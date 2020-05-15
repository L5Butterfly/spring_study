2.1 配置git

首先，是指定用户名和邮箱：

$ git config --global user.name "Your Name"
$ git config --global user.email "youremail@domain.com"
1
2
可以如下查看配置信息：

$ git config --list

2.2 创建一个本地repository

创建一个名为myGitTest的repository:

$ git init myGitTest

git_init

然后切换，文件路径到myGitTest：

$ cd myGitTest

依次添加文件README和sample.cpp

$ gedit README

$ gedit sample.cpp

在README文件内随便写入一些内容：

This is my first Git and GitHub test conducted on my Ubuntu Wily system.
1
同理，在sample.cpp中写入一段代码：

#include <iostream>

int main()
{
    std::cout << "Hello Git!" << std::endl;
    return 0;
}

将这两个文件通过git添加到刚刚创建的myGitTest：

$ git add README

$ git add smaple.cpp

现在，将myGitTest的变化更新情况提交：

$ git commit -m "create a git project"