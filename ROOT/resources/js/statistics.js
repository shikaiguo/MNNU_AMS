require.config({
	paths : {
		echarts : 'http://echarts.baidu.com/build/dist'
	}
});
$('#statistics #condition').change(function() {
	var s = "";
	switch (parseInt($(this).find("option:selected").index())) {
		case 1:
			s+="学院选择：<select id=\"seldept\"><option>所有</option><option>艺术学院</option><option>计算机学院</option></select>";
			break;
		default:
			s = "";
	}
	$('#statistics #detail').html(s);
});
$('#statistics #statisticsbtn').click(function() {
	var fw = "";
	switch (parseInt($(this).parent().find("option:selected").index())) {
		case 0:
			alert("请选择统计条件");
			break;
		case 1:
			var dept=$('#statistics #seldept').find("option:selected").text();
			var jobs="";
			$.ajax({
				url:'Admin/getJobType/'+dept,type:'post',dataType:'json',success:function(res){
					jobs=res;
				}
			});
			$.ajax({
				url : 'Admin/getJobStatistics/'+dept, type : 'post', dataType : 'json', success : function(res) {
					require([ 'echarts', 'echarts/chart/pie','echarts/chart/funnel' ], function(ec) {
						var myChart = ec.init(document.getElementById('main'));
						var option = {
							title : {
								text : '行业人数统计', subtext : '闽南师范大学', x : 'center'
							}, tooltip : {
								trigger : 'item', formatter : "{a} <br/>{b} : {c} ({d}%)"
							}, legend : {
								orient : 'vertical', x : 'left', data : jobs
							}, toolbox : {
								show : true, feature : {
									mark : {
										show : true
									}, dataView : {
										show : true, readOnly : false
									}, magicType : {
										show : true, type : [ 'pie', 'funnel' ], option : {
											funnel : {
												x : '25%', width : '50%', funnelAlign : 'left', max : 1548
											}
										}
									}, restore : {
										show : true
									}, saveAsImage : {
										show : true
									}
								}
							}, calculable : true, series : [ {
								name : '人数', type : 'pie', radius : '55%', center : [ '50%', '60%' ], data : res
							} ]
						};
						myChart.setOption(option, true);
					});
				}
			});
			break;
		case 2:
			$.ajax({
				url : 'Admin/getDeptStatistics', type : 'post', dataType : 'json', success : function(res) {
					require([ 'echarts', 'echarts/chart/pie','echarts/chart/funnel' ], function(ec) {
						var myChart = ec.init(document.getElementById('main'));
						var option = {
							title : {
								text : '学院毕业人数统计', subtext : '闽南师范大学', x : 'center'
							}, tooltip : {
								trigger : 'item', formatter : "{a} <br/>{b} : {c} ({d}%)"
							}, legend : {
								orient : 'vertical', x : 'left', data : [ '文学院', '外国语学院', '法学与公共管理学院', '数学与统计学院', '物理与信息工程学院', '化学与环境学院', '历史与社会发展学院', '计算机学院', '教育科学学院', '体育学院', '经济学院', '管理学院', '生物科学与技术学院', '艺术学院', '新闻传播学院', '马克思主义学院' ]
							}, toolbox : {
								show : true, feature : {
									mark : {
										show : true
									}, dataView : {
										show : true, readOnly : false
									}, magicType : {
										show : true, type : [ 'pie', 'funnel' ], option : {
											funnel : {
												x : '25%', width : '50%', funnelAlign : 'left', max : 1548
											}
										}
									}, restore : {
										show : true
									}, saveAsImage : {
										show : true
									}
								}
							}, calculable : true, series : [ {
								name : '人数', type : 'pie', radius : '55%', center : [ '50%', '60%' ], data : res
							} ]
						};
						myChart.setOption(option, true);
					});
				}
			});
			break;
		case 3:
			fw = "getFromStatisticsAll";
		case 4:
			if (fw == "")
				fw = "getWorkStatisticsAll";
			$.ajax({
				url : "Admin/" + fw, type : "post", dataType : "json", success : function(res) {
					require([ 'echarts', 'echarts/chart/map' ], function(ec) {
						var myChart = ec.init(document.getElementById('main'));
						var option = {
							tooltip : {
								trigger : 'item'
							}, toolbox : {
								show : true, orient : 'vertical', x : 'right', y : 'center', feature : {
									mark : {
										show : true
									}, dataView : {
										show : true, readOnly : false
									}
								}
							}, series : [ {
								tooltip : {
									trigger : 'item', formatter : '{b}'
								}, name : '选择器', type : 'map', mapType : 'china', mapLocation : {
									x : 'left', y : 'top', width : '30%'
								}, roam : true, selectedMode : 'single', itemStyle : {
									// normal:{label:{show:true}},
									emphasis : {
										label : {
											show : true
										}
									}
								}, data : [ {
									name : '北京', selected : false
								}, {
									name : '天津', selected : false
								}, {
									name : '上海', selected : false
								}, {
									name : '重庆', selected : false
								}, {
									name : '河北', selected : false
								}, {
									name : '河南', selected : false
								}, {
									name : '云南', selected : false
								}, {
									name : '辽宁', selected : false
								}, {
									name : '黑龙江', selected : false
								}, {
									name : '湖南', selected : false
								}, {
									name : '安徽', selected : false
								}, {
									name : '山东', selected : false
								}, {
									name : '新疆', selected : false
								}, {
									name : '江苏', selected : false
								}, {
									name : '浙江', selected : false
								}, {
									name : '江西', selected : false
								}, {
									name : '湖北', selected : false
								}, {
									name : '广西', selected : false
								}, {
									name : '甘肃', selected : false
								}, {
									name : '山西', selected : false
								}, {
									name : '内蒙古', selected : false
								}, {
									name : '陕西', selected : false
								}, {
									name : '吉林', selected : false
								}, {
									name : '福建', selected : false
								}, {
									name : '贵州', selected : false
								}, {
									name : '广东', selected : false
								}, {
									name : '青海', selected : false
								}, {
									name : '西藏', selected : false
								}, {
									name : '四川', selected : false
								}, {
									name : '宁夏', selected : false
								}, {
									name : '海南', selected : false
								}, {
									name : '台湾', selected : false
								}, {
									name : '香港', selected : false
								}, {
									name : '澳门', selected : false
								} ]
							} ], animation : false
						};
						var ecConfig = require('echarts/config');
						myChart.on(ecConfig.EVENT.MAP_SELECTED, function(param) {
							var selected = param.selected;
							var selectedProvince;
							var name;
							for (var i = 0, l = option.series[0].data.length; i < l; i++) {
								name = option.series[0].data[i].name;
								option.series[0].data[i].selected = selected[name];
								if (selected[name]) {
									selectedProvince = name;
								}
							}
							if (typeof selectedProvince == 'undefined') {
								option.series.splice(1);
								option.legend = null;
								option.dataRange = null;
								myChart.setOption(option, true);
								return;
							}
							option.series[1] = {
								name : '人数', type : 'map', mapType : selectedProvince, itemStyle : {
									normal : {
										label : {
											show : true
										}
									}, emphasis : {
										label : {
											show : true
										}
									}
								}, mapLocation : {
									x : '35%'
								}, roam : true, data : res
							};
							option.legend = {
								x : 'right', data : [ '人数' ]
							};
							option.dataRange = {
								orient : 'horizontal', x : 'right', min : 0, max : 1000, color : [ 'orange', 'yellow' ], text : [ '多', '少' ], // 文本，默认为数值文本
								splitNumber : 0
							};
							myChart.setOption(option, true);
						});
						myChart.setOption(option, true);
					});
				}, error : function() {
					alert("请求失败");
				}
			});
			break;
		case 5:
			break;
		default:
			alert("未完成");
	}
});
window.onload = function() {
	$('#main').height(document.body.clientHeight);
};
window.onresize = function() {
	$('#main').height(document.body.clientHeight);
};