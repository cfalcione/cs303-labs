
LEFT, RIGHT = False, True
identity = lambda x: x

class BTNode():

    def __init__(self, key, value):
        self.value = value
        self.parent = None
        self.left = None
        self.right = None
    
    def get_child(self, direction):
        return self.right if direction == RIGHT else self.left
    
    def set_child(self, direction, node):
        if node is not None:
            node.parent = self
        if direction == RIGHT:
            self.right = node
            return
        self.left = node
    
    def get_direction(self, child):
        return LEFT if self.left is child else RIGHT



class AbstractBST():
    def __init__(self, key=identity):
        self.key = key
        self.root = None
        self.rotate_left = lambda pivot: self.rotate(LEFT, pivot)
        self.rotate_right = lambda pivot: self.rotate(RIGHT, pivot)
    
    def insert_many(self, *elements):
        for element in elements:
            self.insert(element)

    def insert(self, element):
        raise NotImplementedError
    
    def set_root(self, node):
        self.root = node
        node.parent = None

    def search(self, target):
        node = self.root

        while node is not None:
            node_key = self.key(node.value)
            if node_key == target:
                return node.value
            if node_key < target:
                node = node.right
                continue
            node = node.left

        return None

    def inorder(self):
        yield from map(lambda node: node.value, self._inorder(self.root))

    def _inorder(self, node):
        if node is None:
            return
        yield from self._inorder(node.left)
        yield node
        yield from self._inorder(node.right)
    
    
    def rotate(self, direction, pivot):
        child = pivot.get_child(not direction)
        parent = pivot.parent
        if child is None:
            return
        pivot.set_child(not direction, child.get_child(direction))
        child.set_child(direction, pivot)

        return self._fix_parents_child(parent, pivot, child)


    def _fix_parents_child(self, parent, prev_child, node):
        if parent is None:
            self.set_root(node)
            return
        parent.set_child(parent.get_direction(prev_child), node)
    


class BinarySearchTree(AbstractBST):
    
    def insert(self, element):
        node = BTNode(self.key(element), element)
        self.insert_node(node)
    
    def insert_node(self, node):
        if self.root is None:
            self.root = node
            return
        self._insert(self.root, node)
    
    def _insert(self, current, new):
        child_key = self.key(current.value) <= self.key(new.value)
        self._insert_child(current, child_key, new)
    
    def _insert_child(self, current, child_direction, new_node):
        child = current.get_child(child_direction)
        if child is None:
            current.set_child(child_direction, new_node)
            return
        self._insert(child, new_node)
