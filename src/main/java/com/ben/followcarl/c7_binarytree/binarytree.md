## 种类

    - 满二叉树

        - 只有度为0或2的结点
        - 且度为0的结点在同一层

    - 完全二叉树

        - 最底层没有填满，其余每层都填满了
        - 最底层的结点都在该层最左边的位置
        -

    - 二叉搜索树

        - 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
        - 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
        - 它的左、右子树也分别为二叉排序树


    - 平衡二叉搜索树

        - AVL（Adelson-Velsky and Landis）树
        - 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。

## 存储方式

    - 链式存储

        - 指针

            -

    - 顺序存储

        - 数组

            - 如果父节点的数组下标是 i，那么它的左孩子就是 i * 2 + 1，右孩子就是 i * 2 + 2。
            -
## 遍历方式

    - 深度优先遍历(递归或迭代实现)

        - 前序遍历

            - 中左右

        - 中序遍历

            - 左中右

        - 后序遍历

            - 左右中

        - 理解
            - 前序遍历第一个元素是头节点
            - 后序遍历最后一个元素是头节点

    - 广度优先遍历

        - 一层一层的遍历

- 定义一个二叉树
```java
 public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

```

