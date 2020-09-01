# nvm

`nvm`是`Node.js`的版本管理器，可以让我们方便地切换`Node.js`的版本。



## 使用git安装

```bash
cd ~/
git clone https://github.com/nvm-sh/nvm.git .nvm
cd ~/.nvm
git checkout v0.35.2
```

然后将下面的指令加入你的`.bashrc`/`.zshrc`中

```bash
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion
```

## Windows版本

参考[Windows上安装nodejs版本管理器nvm](https://www.jianshu.com/p/324044f2f542)