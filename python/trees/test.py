from unittest import main, TestCase
from random import randint, shuffle
from csv import reader
from timeit import default_timer

from bst import BinarySearchTree
from rbt import RedBlackTree, RED, BLACK


basic_cases = [
    [ 7, 2, 3, 8, 9, 5 ],
    [ 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4 ],
    [ randint(-10000, 10000) for _ in range(1000) ],
    [ "should", "work", "on", "any", "comparable" ],
    [ ('a', 0), ('b', 1), ('a', 1), ('b', 1) ],
]

class BinarySearchTreeTest(TestCase):

    def test_trees(self):
        for case in basic_cases:
            with self.subTest(type="binary search tree", case=case):
                self._test_bst_case(case, BinarySearchTree())
            with self.subTest(type="red-black tree", case=case):
                tree = RedBlackTree()
                self._test_bst_case(case, tree)
                self._test_rbt(tree)
    
 
    def test_upc_bst(self):
        upc_data = read_csv("UPC.csv")
        expected_data = read_csv("input.dat")
        shuffle(upc_data)

        tree = BinarySearchTree()
        self._test_bst_case(upc_data, tree)
        
        time_before = default_timer()
        for expected in expected_data:
            self.assertTrue( tree.search(expected) )
        print("BST Search time: {}".format(default_timer() - time_before))
        
    
    def test_upc_rbt(self):
        upc_data = read_csv("UPC-random.csv")
        expected_data = read_csv("input.dat")

        key_func = lambda row: row[0] # the unique identifier of the row
        tree = RedBlackTree(key=key_func)
        self._test_bst_case(upc_data, tree, key_func)
        self._test_rbt(tree)
        
        time_before = default_timer()
        for expected in expected_data:
            search = tree.search( key_func(expected) )
            self.assertEqual(search[2], expected[2]) # descriptions
        print("RBT Search time: {}".format(default_timer() - time_before))


    def _test_bst_case(self, case, tree, key=lambda x: x):
        self.assertIsNone( tree.search(case[0]) )

        for elem in case:
            tree.insert(elem)
            self.assertIsNotNone( tree.search(key(elem)) )

        inorder = list(tree.inorder())
        self.assertEqual( inorder, list(sorted(case)) )
    
    def _test_rbt(self, tree):
        root = tree.root
        self.assertColor( root, BLACK)
        self._test_rbt_node_coloring(root)   
        self._test_rbt_node_black_height(root) 
    
    def _test_rbt_node_coloring(self, node):
        if node is None:
            return
        left, right = node.left, node.right
        if node.color is RED:
            self.assertColor(left, BLACK)
            self.assertColor(right, BLACK)
        self._test_rbt_node_coloring(left)
        self._test_rbt_node_coloring(right)
    
    def _test_rbt_node_black_height(self, node):
        if node is None:
            return 1
        left, right = node.left, node.right
        left_height = self._test_rbt_node_black_height(left)
        right_height = self._test_rbt_node_black_height(right)
        self.assertEqual( left_height, right_height )
        return left_height + (1 if node.color is BLACK else 0)

    def assertColor(self, rbnode, color):
        if rbnode is None:
            return
        self.assertEqual(rbnode.color, color)
    
    def test_rotate_right(self):
        a, x, b, y, c = 1, 2, 3, 4, 5
        tree = BinarySearchTree()
        tree.insert_many(y, x, a, b, c)

        tree.rotate_right(tree.root)
        
        root = tree.root
        self.assertEqual( root.value, x )
        self.assertEqual( root.left.value, a )
        self.assertEqual( root.left.parent, root )
        self.assertEqual( root.right.parent, root )
        yNode = root.right
        self.assertEqual( yNode.value, y )
        self.assertEqual( yNode.left.value, b )
        self.assertEqual( yNode.right.value, c )
        
        inorder = list(tree.inorder())
        self.assertEqual( inorder, [a, x, b, y, c] )
    
    def test_rotate_left(self):
        a, x, b, y, c = 1, 2, 3, 4, 5
        tree = BinarySearchTree()
        tree.insert_many(x, y, a, b, c)

        tree.rotate_left(tree.root)
        
        root = tree.root
        self.assertEqual( root.value, y )
        self.assertEqual( root.right.value, c )
        self.assertEqual( root.left.parent, root )
        self.assertEqual( root.right.parent, root )
        x_node = root.left
        self.assertEqual( x_node.value, x )
        self.assertEqual( x_node.left.value, a )
        self.assertEqual( x_node.right.value, b )
        
        inorder = list(tree.inorder())
        self.assertEqual( inorder, [a, x, b, y, c] )
   
        


def read_csv(filename):
    with open(filename, 'r') as file:
        return list( map(tuple, reader(file)) )


if __name__ == "__main__":
    main()