# UI-layout
移动应用开发实验二

- Android studio默认有一个ConstraintLayout，没有影响。

- main函数最外层好像不能给数组类型赋初值？至少不能直接赋值，但是不赋初值也没有影响。

- TextView类型不能声明为main里的变量，否则程序会打不开，那就在用的函数里声明。

- Layout_weight只有在height和width不全是match_parents是有效，效果是按权重分配剩余空间。
- 彩蛋：表面上是四种控件两个类别，但是三块颜色蓝-白-红会看到第五种~