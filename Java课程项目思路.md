### Java课程项目思路

##### 1、构建KeyValueStorage类：

- 定义hash值计算函数：实现文件或文件夹的hash值计算。

- 定义文件读取函数：实现文件的读取，将value存储到输入流中。

- 定义文件写入函数：通过输入流中的value计算hash值，将hash值作为文件名创建一个新的文件，将value写入文件中。

- 定义查找value函数：通过输入key值可以返回对应的value。

- 定义生成key函数：输入新的value以后，可以生成并返回对应的key值。

##### 2、构建Blob类继承自KeyValueStorage类：

- 定义生成key函数：文件的输入方式为文件所在路径，读取文件路径并生成hash值（此处为对文件内容进行hash）。
- 定义返回文件名函数：返回文件的文件名。
- 定义返回key值函数：返回文件对应的key。
- 设定文件类型为Blob。

##### 3、构建Tree类继承自KeyValueStorage类：

- 定义生成key函数：遍历文件夹内的所有文件夹与文件，通过文件类型、hash值、文件名构成的字符串数组构造hash值（此处为对字符串进行hash）。Tree类型的value只显示该文件夹内的所有内容，不用显示子文件夹中内容。
- 定义返回文件名函数：返回文件夹名称。
- 定义返回key值函数：返回文件夹对应的key。
- 设定文件类型为Tree。

##### 4、构建Commit类继承自KeyValueStorage类：

- 定义判断commit函数：对于新提交的commit，计算文件的hash值，通过深度遍历，计算文件与文件夹的hash值。并与前一个commit比对，如果hash值相同，则不再重复生成文件，直接将hash值存入树中，如果hash值不同，则生成新的文件，并更新整个commit对应的tree。如果与前一个commit没有不同，则不是符合条件的commit，不进行添加操作。
- 定义生成hash值函数：通过本次commit对应的内容的信息，提交时间，提交人，作者，前一个commit的信息所构成的字符串数组构造hash值（此处为对字符串进行hash）。
- 定义添加commit函数：定义链表用于存储commit，新的commit按照头插法加入原链表中，每一个commit有与之对应的根目录的key。
- 定义查看commit函数：显示本次commit的信息，提交时间，提交人，作者，前一个commit的信息。
- 定义打印commit函数：从最新commit开始进行遍历，答应出每个commit对应的名称，第几次commit，commit的hash值。
- 定义返回key值函数：返回commit对应的key。
- 设定文件类型为Commit。

##### 5、设置Head指针：

- 定义生成head指针函数：存储指定分支的hash值。
- 定义更新head指针函数：进行分支切换时，或回滚操作时，更新Head指针存储的内容为新分支的hash值。

##### 6、定义master分支：

- 设置master主分支，在refs文件夹中生成名为master的文件，文件内存储最新commit对应的hash值。

##### 7、构建Branch类继承自KeyValueStorage类：

- 定义生成branch函数：在refs文件夹中生成以branch的名字命名的文件，文件内存储Head指针对应的hash值。
- 定义更新branch函数：在branch分支上，根据提交的commit，更新为最新commit的hash值。
- 定义查看branch函数：显示branch的信息，hash值。
- 定义文件类型为branch。

##### 8、回滚Reset：

- 定义显示函数：打印出所有可以回滚的commit，遍历refs内的所有分支结构，根据分支中存储的最新commit的hash值进行遍历并打印。
- 定义回滚操作函数：输入为指定commit的hash值，通过hash值，在key-value仓库中恢复文件。遇到Tree类型，则在现有工作路径后添加文件夹名，生成新的路径；遇到Blob类型，则生成名为Blob对应文件名的文件，将key对应的value复制到文件中。

##### 9、文本比对Diff：

- 定义计算LCS（最长公共子序列）长度函数：该函数将input1[1..m]和input2[1..n]作为输入序列，计算所有1≤i≤m和1≤j≤n的input1[1..i]和input2[1..j]之间的LCS，并将其存储在opt[i，j]中。opt[m，n]将包含input1和input2的LCS的长度。
- 定义生成Diff函数：此函数将回溯opt矩阵，并打印两个序列之间的差异。请注意，如果使用>和≤交换≥和<，将得到不同的答案。

##### 10、测试（目标是后期转换为交互界面）：

###### 初期实现：通过Scanner接收用户指令，将已实现的功能通过标号列举出来，用户可以通过选择标号，实现对应的操作。

###### 后期实现目标：通过main函数命令行参数String[] args接收用户指令。

- 生成一个project：用户输入一个路径，可以通过路径生成对应的key-value键值对，默认为第一个commit，并自动设置为master分支，更新Head值。

- 提交一个commit：用户输入一个路径，或者改变了某个文件或文件夹，可以生成一个新的key-value键值对，按头插法插入用户所在分支的commit链表中。
- 创建一个branch：用户输入一个branch名，生成一个新的branch。
- 切换branch：用户提交切换branch命令后，所有改变将只会在branch上发生。
- 查看commit操作：用户可以看到所有已有commit，以便于对其进行操作。
- 回滚：根据查找的需要回滚的commit的hash值，进行回滚操作。