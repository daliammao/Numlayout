# NumLayout

[apk下载](https://github.com/daliammao/Numlayout/raw/master/app/apk/app-debug.apk)

有时候编辑数字总会有非常多的限制，比如购买商品有起购、限购和库存，不满足限制，数字还要修正，重复写这些东西非常烦人而且也造成了代码冗余。

<div class='row'>
    <img src='https://raw.githubusercontent.com/daliammao/Numlayout/master/image1.jpg' width="300px"/>
</div>

```java
LayoutConfigBuilder.newLayoutConfigBuilder()
	.setOrientation(LayoutConfig.HORIZONTAL)
	.setBackgroundResource(R.drawable.shape_cart_stroke)
	.setHeight(ViewConfig.WRAP_CONTENT)
	.setWidth(ViewConfig.WRAP_CONTENT)
	.addChild(BtnConfigBuilder.newBtnConfigBuilder()
		.setTag("Les")
		.setWidth(UIUtils.dip2px(context, 30))
		.setHeight(ViewConfig.MATCH_PARENT)
		.setText("-")
		.btnWithLes()
		.build())
	.addChild(EditConfigBuilder.newEditConfigBuilder()
		.setTag("Text")
		.textWithInteger(min, max, tolerance, true)
		.setHeight(ViewConfig.MATCH_PARENT)
		.setWidth(UIUtils.dip2px(context, 35))
		.setText(String.valueOf(min))
		.setGravity(Gravity.CENTER)
		.setBackgroundResource(R.color.trans)
		.setTextSize(13)
		.build())
	.addChild(BtnConfigBuilder.newBtnConfigBuilder()
		.setTag("Add")
		.setWidth(ViewConfig.WRAP_CONTENT)
		.setWidth(UIUtils.dip2px(context, 30))
		.setHeight(ViewConfig.MATCH_PARENT)
		.btnWithAdd()
		.setText("+")
		.build())
	.build();
```
使用链式调用绘制UI

```java
	numLayout.setOnItemClickListener(new NumLayout.OnItemClickListener() {
            @Override
            public void onItemClick(NumLayout parent, View view, ViewConfig.ViewType type, String tag) {
                if (tag.equals("Les")) {
                    parent.lesNumWithTag("Text");
                } else if (tag.equals("Add")) {
                    parent.addNumWithTag("Text");
                }
            }
        });
```
numLayout的点击事件统一管理，如果出发加事件，需要调用addNumWithTag。

```java
        numLayout.setOnNumComputeHandler(new NumLayout.OnNumComputeHandler() {
            @Override
            public String onNormalTextComputeHandler(String tag, CharSequence oldText, NumLayout.Operation action, float minNum, float maxNum, float tolerance) {
                return null;
            }

            @Override
            public Integer onIntegerNumComputeHandler(String tag, int oldNum, NumLayout.Operation action, int minNum, int maxNum, int tolerance) {
                return null;
            }

            @Override
            public Float onDecimalNumComputeHandler(String tag, float oldNum, NumLayout.Operation action, float minNum, float maxNum, float tolerance) {
                return null;
            }
        });
```
指定的tag被触发了+或-的操作，要怎么处理？通过返回值返回处理后的新值。返回null会使用默认逻辑加或减指定公差。

```java
        numLayout.setOnIntegerErrorListenerWithTag("Text", new OnIntegerErrorListener() {
            @Override
            public Integer onIntegerError(ErrorType errorType, int newData, int min, int max, int tolerance) {
                switch (errorType) {
                    case LT_MIN:
                        Toast.makeText(MainActivity.this,"LT_MIN",Toast.LENGTH_SHORT).show();
                        break;
                    case GT_MAX:
                        Toast.makeText(MainActivity.this,"GT_MAX",Toast.LENGTH_SHORT).show();
                        break;
                    case LT_ZERO:
                        Toast.makeText(MainActivity.this,"LT_ZERO",Toast.LENGTH_SHORT).show();
                        break;
                    case NOT_SATISFY_TOLERANCE:
                        Toast.makeText(MainActivity.this,"NOT_SATISFY_TOLERANCE",Toast.LENGTH_SHORT).show();
                        break;
                }
                return null;
            }
        });
```
当指定tag发生错误，会在这里触发，可以返回处理后的新值，返回null会进行默认处理

```java
        numLayout.setOnInputListener(new NumLayout.OnInputListener() {
            @Override
            public void onInputComplete(NumLayout parent, String tag, String text, float min, float max, float tolerance) {
                System.out.println(text);
            }
        });
```
最后的输入结果在这里监听