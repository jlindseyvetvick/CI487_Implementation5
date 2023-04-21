public class AVL<E extends Comparable> {

	public class TreeNode<E extends Comparable> {
		E data;
		int height;
		TreeNode<E> left;
		TreeNode<E> right;

		public TreeNode(E data) {
			this.data = data;
			left = null;
			right = null;
			height = 0;
		}

	}

	private TreeNode<E> root;

	public AVL() {
		root = null;
	}

	public void inorderTraversal() {
		inorderTraversal(root);
	}

	private void inorderTraversal(TreeNode<E> node) {
		if (node != null) {
			inorderTraversal(node.left);
			System.out.println(node.data);
			inorderTraversal(node.right);
		}
	}

	private TreeNode<E> findMinNode(TreeNode<E> curr) {

		if (curr == null) {
			return null;
		}

		while (curr.left != null) {
			curr = curr.left;
		}

		return curr;
	}

	/*
	 * Step 1: Modify these methods to: 1) Update Height and 2) balance curr upon
	 * returning
	 */
	public void insert(E data) {
		root = insert(root, data);
	}

	public int getHeight(TreeNode<E> n) {
		return n == null ? -1 : n.height;
	}

	public int updateHeight(TreeNode<E> curr) {
		if (curr != null) {
			return curr.height = Math.max(getHeight(curr.left), getHeight(curr.right)) + 1;
		} else {
			return -1;
		}
	}

	private TreeNode<E> insert(TreeNode<E> curr, E data) {

		if (curr == null) {
			curr = new TreeNode<>(data);
			return curr;
		} else if (data.compareTo(curr.data) < 0) {
			curr.left = insert(curr.left, data);
		} else {
			curr.right = insert(curr.right, data);
		}

		updateHeight(curr);
		return balance(curr);
	}

	public void remove(E data) {
		root = remove(root, data);
	}

	private TreeNode<E> remove(TreeNode<E> curr, E data) {

		if (curr == null) {
			return null;
		}

		if (curr.data.compareTo(data) < 0) {
			curr.right = remove(curr.right, data);
		} else if (curr.data.compareTo(data) > 0) {
			curr.left = remove(curr.left, data);
		} else {
			if (curr.left == null) {
				return curr.right;
			} else if (curr.right == null) {
				return curr.left;
			} else {
				TreeNode<E> minNode = findMinNode(curr.right);
				curr.data = minNode.data;
				curr.right = remove(curr.right, minNode.data);
			}
		}
		updateHeight(curr);
		return balance(curr);
	}

	/*
	 * Implement the rest of the following methods to finish transforming the BST
	 * into an AVL tree.
	 */

	public int getBF(TreeNode<E> n1) {
		return getHeight(n1.left) - getHeight(n1.right);
	}

	private TreeNode<E> balance(TreeNode<E> curr) {
		int BF = getBF(curr);

		if (BF > 1) {
			if (getBF(curr.left) < 0) {
				curr = leftRightRotate(curr);
			} else {
				curr = rightRotate(curr);
			}
		} else if (BF < -1) {
			if (getBF(curr.right) > 0) {
				curr = rightLeftRotate(curr);
			} else {
				curr = leftRotate(curr);
			}
		}
		return curr;
	}

	private TreeNode<E> leftRotate(TreeNode<E> node) {

		TreeNode<E> tmp = node.right;
		node.right = tmp.left;
		tmp.left = node;

		updateHeight(node);
		updateHeight(tmp);
		return tmp;
	}

	private TreeNode<E> rightRotate(TreeNode<E> node) {
		TreeNode<E> tmp = node.left;
		node.left = tmp.right;
		tmp.right = node;

		updateHeight(node);
		updateHeight(tmp);
		return tmp;
	}

	private TreeNode<E> leftRightRotate(TreeNode<E> node) {
		node.left = leftRotate(node.left);
		return rightRotate(node);
	}

	private TreeNode<E> rightLeftRotate(TreeNode<E> node) {
		node.right = rightRotate(node.right);
		return leftRotate(node);
	}

	/* Step 3) Implement search method that measures steps to find node */

	public int search(E data) {
		return search(root, data);
	}

	private int search(TreeNode<E> curr, E data) {
		int iterationCount = 0;

		while (curr != null && curr.data != data) {
			if (curr.data.compareTo(data) > 0) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
			iterationCount++;
		}
		return iterationCount;
	}
}
