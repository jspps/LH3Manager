# LH3Manager
learnhall manager - 学习大厅 后台管理

操作都在项目右键 --> properties 

1 . Java Build Path
  Source 中设置 Default output folder 为 xxx/WebRoot/WEB-INF/classes
  Libraries 中设置 Add Library
2 . MyEclipse
  Project Facets 中选中 
	Dynamic Web Module = 3.0
	Java = 1.7
	JavaScript = 1.0
  Web 中 设置 Web Context-root
  Deployment Assembly 下面勾选Use xxx后修改下面的
	source和DeployPath如下
	/src - > WEB-INF/classes
	/WebRoot  -> /
其实就是添加两个source资源一个是/src(Java代码),一个是/WebRoot（jsp界面代码和lib的jar包）