from bst import BinarySearchTree, BTNode, identity, LEFT, RIGHT

RED=True
BLACK=False

class RedBlackNode(BTNode):
    def __init__(self, key, value, color = RED):
        super().__init__(key, value)
        self.color = color
    
    def __str__(self):
        color = 'b' if self.color is BLACK else 'r'
        return "{}|{}: ( {}, {} )".format(color, self.value, str(self.left), str(self.right))

class RedBlackTree(BinarySearchTree):
    def __init__(self, key=identity):
        super().__init__(key)
    
    def insert(self, element):
        node = RedBlackNode(self.key(element), element)
        super().insert_node(node)
        self.post_insert_fix(node)
    
    def post_insert_fix(self, node):
        if node is self.root:
            node.color = BLACK
            return
        node.color = RED
        
        parent = node.parent # can't be None b/c node isn't root
        grandparent = parent.parent
        if parent.color is BLACK:
            return
        # grandparent can't be None b/c that'd imply that the root (parent) is red
        uncle_direction, uncle_color = self._get_sibling_params(parent)
        if uncle_color is RED:
            # uncle must exist
            grandparent.get_child(uncle_direction).color = BLACK
            parent.color = BLACK
            grandparent.color = RED
            return self.post_insert_fix(grandparent)
        
        node_direction = parent.get_direction(node)
        if node_direction is uncle_direction:
            # triangle case
            self.rotate(not node_direction, parent)
            return self.post_insert_fix(parent)
        # line case
        self.rotate(not node_direction, grandparent)
        grandparent.color = RED
        parent.color = BLACK
        

    def _get_sibling_params(self, node):
        # assumes parent exists
        parent = node.parent
        direction = not parent.get_direction(node)
        sibling = parent.get_child(direction)
        if sibling is None:
            return direction, BLACK
        return direction, sibling.color
    

