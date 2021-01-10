### Java课程项目思路

##### 1、构建GitObject类：

- 定义hash值计算函数FileSHA1Checksum()：实现文件的hash值计算。
- 定义hash值计算函数StringSHA1Checksum()：实现文件夹的hash值计算。
- 定义文件写入函数WriteToFile()：根据指定路径下文件的value计算hash值，将hash值作为文件名创建一个新的文件，将value写入文件中。
- 定义字符串写入函数WriteToString()：根据字符串的value计算hash值，将hash值作为文件名创建一个新的文件，将value写入文件中。
- 定义查找value函数GetValue()：通过输入key值可以返回对应的value。
- 定义生成key函数GetKey()：输入新的value以后，可以生成并返回对应的key值。


##### 2、构建Blob类继承自GitObject类：

- 继承FileSHA1Checksum()：文件的输入方式为文件所在路径，读取文件路径并生成hash值（此处为对文件内容进行hash）。
- 定义返回key值函数GetKey()：返回文件对应的key。
- 设定文件类型为Blob，访问权限为100644。

##### 3、构建Tree类继承自GitObject类：

- 继承StringSHA1Checksum()：遍历文件夹内的所有文件夹与文件，通过文件访权限、文件类型、hash值、文件名构成的字符串数组构造hash值（此处为对字符串进行hash）。Tree类型的value只显示该文件夹内的所有内容，不用显示子文件夹中内容。
- 定义返回key值函数GetKey()：返回文件夹对应的key。
- 设定文件类型为Tree，访问权限为040000。

##### 4、构建Commit类继承自GitObject类：

- 判断commit函数：对于新提交的commit，计算文件的hash值，通过深度遍历，计算文件与文件夹的hash值。并与前一个commit对应的tree的key值比对，如果相同，则不是符合条件的commit，不进行添加操作。
- 定义生成hash值函数ComputeCommit()：通过本次commit对应的tree的hash值，提交人，作者，提交时间，提交时区，前一个commit的hash值以及评论所构成的字符串数组构造hash值（此处为对字符串进行hash）。
- 定义创建commit函数Commit()：根据logs下的user.txt获取用户的用户名以及邮箱，根据提交的内容以及评论生成commit。
- 定义显示commitID函数ShowCommitList()：定义链表用于存储commit，新的commit按照头插法加入原链表中，每一个commit有与之对应的根目录的key。
- 定义返回key值函数GetKey()：返回commit对应的key。
- 设定文件类型为Commit。

##### 5、构建Ref类：

- 定义字符串写入函数Write()：将字符串写入目标路径。
- 定义文件写入函数WriteToFile()：将指定路径中的文件写入目标路径。
- 定义文件读取函数Readfile()：读取文件内容

##### 6、构建HEAD类继承自Ref类：

- 定义生成HEAD指针函数HEAD()：创建并初始化HEAD文件，用于存储指定分支的hash值。
- 定义更新HEAD指针函数updateHEAD()：进行分支切换时，或回滚操作时，更新HEAD指针存储的内容为新分支的hash值。
- 定义获取HEAD内容函数getHEAD()：获取HEAD指向的分支路径

##### 6、定义master分支：

- 初始设置master为主分支，在refs文件夹中生成名为master的文件，文件内存储最新commit对应的hash值。

##### 7、构建Branch类继承自Ref类：


- 定义生成branch函数Branch()：在refs文件夹中生成以branch的名字命名的文件。
- 定义更新branch函数updateBranch()：在branch分支上，根据提交的commit，更新为最新commit的hash值。
- 定义判断branch是否存在函数IsBranchExist()：判断该分支是否存在。
- 定义切换分支函数checkoutBranch()：切换到指定分支。
- 定义查询branch名称函数getBranchName()：返回branch的名字。
- 定义查询branch对应commit函数getCommitId()：返回branch文件内的hash值。

##### 8、构建Log类继承自Ref：

###### 目标：显示某一分支下的所有commit的信息，以便于进行后续操作。

- 定义创建log函数Log()：创建一个log操作。
- 定义通过分支名称显示所有commit函数showCommit()：通过分支名称显示该分支下的所有commit。
- 定义通过某一commit显示所有commit函数showCommitParent()：通过commit的value识别上一个commit。

##### 9、构建Reset继承自Ref：

###### 目标：根据指定commitID恢复其对应的树结构，在设计的时候没有借鉴Git的回滚操作，所以我们的Reset是恢复到某一指定路径，暂未考虑文件删除的操作。

- 定义创建reset函数Reset()：创建一个reset操作。
- 定义reset操作函数ResetObjects()：输入为指定commit的hash值，通过hash值，在key-value仓库中恢复文件。遇到Tree类型，则在现有工作路径后添加文件夹名，生成新的路径；遇到Blob类型，则生成名为Blob对应文件名的文件，将key对应的value复制到文件中。

##### 10、文本比对Diff：

- 定义计算LCS（最长公共子序列）长度函数lcsLength()：该函数将input1[1..m]和input2[1..n]作为输入序列，计算所有1≤i≤m和1≤j≤n的input1[1..i]和input2[1..j]之间的LCS，并将其存储在opt[i，j]中。opt[m，n]将包含input1和input2的LCS的长度。
- 定义生成Diff函数printDiff()：此函数将回溯opt矩阵，并打印两个序列之间的差异。请注意，如果使用>和≤交换≥和<，将得到不同的答案。

##### 11、仓库管理路径：

- managebase：与git相关的文件的总文件
- objects：存储blob，tree，commit的KeyValue键值对
- logs：存储用户个人信息，邮箱等
- refs\head：存储分支文件
- HEAD：存储当前分支的路径

##### 12、单元测试：

- 在单元测试部分，对HashFile、Node、Commit、Blob、Tree、keyValueStorage六个文件分别进行了单元测试。
- 在TestaHashFile中，使用junit单元测试框架、通过给定路径的方式，测试了文件的功能。
- 在NodeTest中，使用main函数，创建节点、填充节点数据并打印节点内容，测试了文件功能的实现。
- 在keyValueStorageTest中，测试获取key的GetKey方法，将文件重新写入并用于测试在GetValue方法中获取到文件，最后测试通过key获取文件并打印文件内容。
- 在BlobTest中，给定文件路径，测试用write方法将文件重新写入并使用GetKey方法获取并打印key，测试Blob文件是否实现功能。
- 在TreeTest中，思路和BlobTest基本相同，给定文件夹路径，通过测试ComputeKey方法获取key并打印key来测试Tree.java文件。
- 在CommitTest中，首先构建提交对象，给定文件夹路径，parent、author、committer、comment，测试Commit文件中的GetKey方法能否获取key，ComputeCommit能否计算提交，showCommit方法能否显示提交记录来测试整个Commit文件的最终功能的实现情况。
- 在六个单元测试中，基本对HashFile、Node、Commit、Blob、Tree、keyValueStorage这六个功能文件实现了测试，确定了其功能实现正常与否，并提示了功能文件的修改和优化，基本实现了进行单元测试的初衷和目的。

##### 13、交互测试：

###### 目标：通过Scanner接收用户指令，根据一些简单的操作指令实现对应操作。


- 通过unitTest文件对gitObject.java,Blob.java,Tree.java,Commit.java,Branch.java,Reset.java,Log.java几个文件进行功能测试，每个功能写一个方法，用main函数调用测试，确保其成功运行，目标功能顺利实现。
- 实现交互界面，模拟git命令行命令，用户输入路径（文件夹或文件皆可），和操作指令（如git-branch、git-reset、git-hash、git-commit、git-value、git-log），程序可以自动完成指令对应的操作，并输出结果和提示返回给用户。
- 加入了循环，用户在输入路径后，可以多次输入指令完成操作；当用户希望结束命令行交互时，可以通过输入git-quit退出循环、结束程序。
- 生成一个project：用户输入一个路径，可以通过路径生成对应的key-value键值对，默认为第一个commit，并自动设置为master分支，更新Head值。
- 查看文件、文件夹的hash值：git-hash指令，可以使用户查看输入路径的hash值。
- 查看文件、文件夹的内容（value）：git-value指令，可以输出用户指定路径的value。如果输入的是单个文件，则输出文件内容；如果输入的是文件夹，则输出文件夹的目录、性质（Blob或Tree）和hash值。
- 提交一个commit：用户输入一个路径，或者改变了某个文件或文件夹，可以生成一个新的key-value键值对，按头插法插入用户所在分支的commit链表中。
- 创建一个branch：用户输入一个branch名，生成一个新的branch，并自动切换到新的branch。
- 查看commit操作：用户输入git-log,可以看到所有已有commit，以便于对其进行操作。
- 回滚：用户输入git-reset,根据输入已查找的需要回滚的commit的hash值，进行回滚操作。
