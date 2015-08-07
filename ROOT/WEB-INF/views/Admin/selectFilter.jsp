<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form  id="myform" class="form-horizontal">  
  <div class="form-group" >
  <div class="col-sm-12">
  <div class="col-sm-5">
  
  <div class="form-group left">
  <label class="col-sm-12 " for=department>可选择显示字段</label>
  </div>
  
  <div class="form-group">
  <div class="col-sm-12">
  <select  multiple name="left" id="left" size="8" style="width:100%;"
  ondblclick="moveOption(document.getElementById('left'), document.getElementById('right'))">  
  </select>  
   </div>
    </div>
   
   
  </div>
  <div class="col-sm-2"  align="center" style="margin-top:10%;">
    <div class="form-group">
  <input type="button" value=" >> " onclick="moveOption(document.getElementById('left'),document.getElementById('right'))"> 
  </div>
  <div class="form-group">
  <input type="button" value=" << " onclick="moveOption(document.getElementById('right'), document.getElementById('left'))">  
   </div>
  </div>
  
   <div class="col-sm-5">
    <div class="form-group left">
   <label class="col-sm-12 " for=department>已选择显示字段</label>
   </div>
   
   <div class="form-group">
   <div class="col-sm-12">
   <select  multiple name="right" id="right" size="8" style='width:100%;'  
  ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))">  
  </select>  
</div>
   </div>
    </div>
   
  </div>
  </div>
   <div class="form-group center" align="center" >
 
	<div class="col-sm-12">
  <INPUT TYPE="button" value="置顶" onclick="moveTop(document.getElementById('right'));">  
  <INPUT TYPE="button" value="上移" onclick="moveUp(document.getElementById('right'));">  
  <INPUT TYPE="button" value="下移" onclick="moveDown(document.getElementById('right'));">  
  <INPUT TYPE="button" value="置底" onclick="moveBottom(document.getElementById('right'));">  
  </div>
   </div>
</form>

<!-- 源码代码 参考-->
<!-- <script type="text/javascript">
    //移动  
 function moveOption(obj1, obj2)  
    {  
         for(var i = obj1.options.length - 1 ; i >= 0 ; i--)  
         {  
             if(obj1.options[i].selected)  
             {  
                var opt = new Option(obj1.options[i].text,obj1.options[i].value);  
                opt.selected = true;  
                obj2.options.add(opt);  
                obj1.remove(i);  
            }  
         }  
    }  
    //置顶  
  function  moveTop(obj)   
  {   
        var  opts = [];   
        for(var i =obj.options.length -1 ; i >= 0; i--)  
        {  
            if(obj.options[i].selected)  
            {  
                opts.push(obj.options[i]);  
                obj.remove(i);  
            }  
        }  
        var index = 0 ;  
        for(var t = opts.length-1 ; t>=0 ; t--)  
        {  
            var opt = new Option(opts[t].text,opts[t].value);  
            opt.selected = true;  
            obj.options.add(opt, index++);  
        }  
    }   
  //置底  
  function  moveBottom(obj)   
  {   
        var  opts = [];   
        for(var i =obj.options.length -1 ; i >= 0; i--)  
        {  
            if(obj.options[i].selected)  
            {  
                opts.push(obj.options[i]);  
                obj.remove(i);  
            }  
        }  
         for(var t = opts.length-1 ; t>=0 ; t--)  
        {  
            var opt = new Option(opts[t].text,opts[t].value);  
            opt.selected = true;  
            obj.options.add(opt);  
        }  
    }   
</script> -->
