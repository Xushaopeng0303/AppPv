# App PV statistics for Android
* 什么是 APP 的 PV
  * 定义：APP 内某个页面展现在前台的次数，只要是被遮挡再回到前台 PV+1;
  * 举例：页面首次展现算一次 PV；跳转到其它页面 Back 回来 PV+1；Home 键再切到前台 PV+1；
  * 简介：Android 页面包括：Activity、Fragment；
* Android 怎么统计PV（前提要了解 Activity 和 Fragment 的生命周期）
  * Activity PV统计:
    * 在onResume() 和 onPause() 处分别添加页面展现 show 和页面被遮挡 hide 的统计；
  * Fragment PV统计：
    * 在onResume() 和 onPause() 处分别添加页面展现 show 和页面被遮挡 hide 的统计？ 答案肯定是不行的，为啥？问题就在于 ViewPager 的懒加载机制；
    * 我们知道 Fragment 的常见使用方式：（1）ViewPager + Fragment；（2）FragmentTransaction + Fragment。
    * ViewPager 会在初始化的时候一次性初始化2个 Fragment，并且这两个 Fragment 都处于 resume 状态。但用户只能看到第一个 Fragment，这时候你能说第二个 Fragment 也显示了吗？我们只能说第二个 Fragment 也准备好了，不能说显示了。这样带来的 Bug 就是明明第二个 Fragment 用户都没有看到，却统计成了已显示。那么还有没有合适的回调方法能够帮助我们准确的统计 Fragment 呢？答案是肯定的。
    * Fragment 有个叫 setUserVisibleHint(boolean) 的方法就是专门设置显示不显示的，ViewPager 会在初始化 Fragment 的时候以及切换 Fragment 的时候调用此方法设置是否显示。那是不是只重写这个方法然后调用 show 和 hide 方法就能完成统计呢？很遗憾，不可以！因为 ViewPager 会在设置 Adapter 之后立即调用第一个、第二个 Fragment 的 setUserVisibleHint(boolean) 方法设置为 false，然后会对第一个 Fragment 再次调用 setUserVisibleHint(boolean)方法设置为true，然后才是 onAttach()、onCreate()，很明显顺序不对。另外当 Fragment 不是在 ViewPager 中使用的时候压根就不会调用 setUserVisibleHint(boolean)方法。
    * 写到此你是否能寻得蛛丝马迹呢？对喽。**要结合 Fragment 的 setUserVisibleHint(boolean)、onResume()、onPause() 这三个方法才能完美的统计。就是在setUserVisibleHint(boolean) 中加上 isResume() 过滤，在 onResume() 和 onPause() 中加上 getUserVisibleHint() 过滤。**
    * 好的，写到这貌似解决完美了 ViewPager + Fragment 懒加载的问题；但是呢？现实当中众多实现都是 ViewPager 嵌套 ViewPager 的实现，比如今日头条。Word 天啊 。刚才才形成的三观瞬间崩塌 
    
    * --------------------------------------高潮来了---------------------------------------
    
    * Fragment 的源码中明确显示，getUserVisibleHint() 方法返回的是 mUserVisibleHint 字段的值，而 mUserVisibleHint 字段默认值是 true。当 ViewPager 嵌套 ViewPager 的时候 子ViewPager 中 Fragment 的 mUserVisibleHint 属性却不会同其 父 Fragment 的 mUserVisibleHint 同步，这样一来 子ViewPager 中 Fragment 的状态的统计就不准确了。。。大写的感慨。。。解决思路在于以下两点：
    * （1）子Fragment onAttach 的时候检查其 父Fragment 的 mUserVisibleHint 属性的状态，如果是 false 就强制将当前 子Fragment 的 mUserVisibleHint 属性设置为 false 并设置一个恢复标记（因为接下来还需要恢复）然后在 父Fragment setUserVisibleHint 为 true 的时候检查其所有 子Fragment，有恢复标记的 子Fragment，设置其 mUserVisibleHint 属性为 true。
    * （2）父Fragment setUserVisibleHint 为 false 的时候将其所有 mUserVisibleHint 为 true 的 子Fragment 都强制改为 false，然后设置一个恢复标记，然后在 父setUserVisibleHint 为 true 的时候再恢复。
    * 详情请下载代码。
* 遇到的问题
  * ViewPager 嵌套 ViewPager 时 父子的 mUserVisibleHint 不同步问题；
  * ViewPager 中 Fragment 比较多时加载几页的问题；通过 ViewPager.setOffscreenPageLimit(limit) 解决；
  * 在统计页面 show 和 hide 时上传类名来统计 PV，为啥？因为统计端见名知意，新增页面不需要修改任何代码；
  * 页面复用问题，可以通过 BaseActivity 和 BaseFragment 中的 setClassName() 做出区分；
* 适用范围：
  * Android APP PV & UV统计（UV可以通过上报的PV 按用户cuid或者帐号去重）；
  * ViewPager 嵌套 ViewPager 时统计问题；
  * 页面复用问题；
  * 核心页面不要频繁Rename；
  
