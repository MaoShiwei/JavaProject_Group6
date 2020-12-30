### Java课程项目思路

##### 1、构建KeyValueStorage类：

- 定义hash值计算函数：实现文件或文件夹的hash值计算。

- 定义文件读取函数：实现文件的读取，将value存储到输入流中。

- 定义文件写入函数：通过输入流中的value计算hash值，将hash值作为文件名创建一个新的文件，将value写入文件中。

- 定义查找value函数：通过输入key值可以返回对应的value。

- 定义生成key函数：输入新的value以后，可以生成并返回对应的key值。

##### 2、构建Blob类继承自KeyValueStorage类：

- 文件的输入方式为文件所在路径，读取文件路径并生成hash值（此处为对文件内容进行hash）。

- 设定文件类型为Blob。

##### 3、构建Tree类继承自KeyValueStorage类：

- 遍历文件夹内的所有文件夹与文件，通过文件类型、哈希值、文件名构成的字符串数组构造hash值（此处为对字符串进行hash）。Tree类型的value只显示该文件夹内的所有内容，不用显示子文件夹中内容。

- 设定文件类型为Tree。

##### 4、构建Commit类继承自KeyValueStorage类：

- 定义判断commit函数：对于新提交的commit，计算文件的hash值，通过深度遍历，计算文件与文件夹的hash值。并与前一个commit比对，如果hash值相同，则不再重复生成文件，直接将hash值存入树中，如果hash值不同，则生成新的文件，并更新整个commit对应的tree。如果与前一个commit没有不同，则不是符合条件的commit，不进行添加操作。

- 定义添加commit函数：定义链表用于存储commit，新的commit按照头插法加入原链表中，每一个commit有与之对应的根目录的key。

- 定义查看commit函数：显示本次commit的信息，提交时间，提交人，作者，前一个commit的信息。Java课程项目思路