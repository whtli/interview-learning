## 谨慎返回Optional
+ 永远不要通过返回Optional的方法返回null，因为这彻底违背了optional的本意
+ Optional本质上与受检异常类似
+ 容器类型包括集合、映射、Stream、数组和optional，都不应该被包装在optional中
+ 如果无法返回结果并且当没有返回结果时客户端必须执行特殊的处理，则应该声明该方法返回Optional<T>，即，返回Optional<T>并不需要任何成本
+ 永远不应该返回基本类型的optional，如Int、Long、Double，但是小的基本类型（Boolean、Byte、Character、Short、Float）除外
+ 几乎永远都不适合用optional作为键、值，或者集合或数组中的元素