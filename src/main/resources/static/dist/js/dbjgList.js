
var currentMenu = "";
var tableDefaultOptions = {
	      'paging'      : true,
	      'processing'  : true,
	      'serverSide'  : true,
	      "pageLength"  :  10,
	      'lengthChange': false,
	      'searching'   : false,
	      'ordering'    : false,
	      'info'        : true,
	      'autoWidth'   : false,
		  'language': {
	            'lengthMenu': '每页显示 _MENU_ 行',
	            'zeroRecords': '未检索到数据，请去掉部分检索条件后重试，或检索其他类型。',
	            'info': '第 _PAGE_ 页/共 _PAGES_ 页（显示第 _START_ ~ _END_条 / 共检索出 _MAX_ 条）',
	            'infoEmpty': '未检索到数据',
	            'infoFiltered': '(从 _MAX_ 条记录中筛选)',
	            'loadingRecords':'加载中...',
	            'loading':'正在搜索...',
	            'processing':'正在搜索...',
	            'paginate':{
	            	'first':'第一页',
	            	'last':'最后一页',
	            	'next':'下一页',
	            	'previous':'上一页'
	            }
	        }
	    };

function queryEvent(){
	//$("#eventQueryForm").serialize()
	var tableOptions = {
			'ajax':{
				'url':'/dbjg/search',
				'method': 'POST',
				'contentType': 'application/json;charset=UTF-8',
				'dataSrc': function(json){
					json.data.forEach(function(e){
						
					});
					return json.data;
				}, 
				'data': function(data){
					data.condition = $("#BatchListQueryForm").serializeObject();
//					data.condition.eventType = currentMenu;
					return JSON.stringify(data);
				}
			},
			'rowCallback': function( row, data, index ) {
				$(row)
					.on("click", function(){
						queryDetailList(data);
			  	});
			},
			'columns':[
				{'data':'batchId'},
				{'data':'compareTime'},
				{'data':'ztryCount'},
				{'data':'matchedCount'}
			]
	};
	$('#BatchTable').DataTable().destroy();
	$("#BatchTable").dataTable($.extend({}, tableOptions, tableDefaultOptions,{'pageLength': 5}));
}


/**
 * 加载批次命中列表
 * @param batchId
 * @returns
 */
function queryDetailList(batchData){
	//$("#eventQueryForm").serialize()
	var tableOptions = {
			'ajax':{
				'url':'/dbjg/details',
				'method': 'POST',
				'contentType': 'application/json;charset=UTF-8',
				'dataSrc': function(json){
					json.data.forEach(function(e){
						
					});
					return json.data;
				}, 
				'data': function(data){
					data.condition = {"batchId":batchData.batchId};
					return JSON.stringify(data);
				}
			},
			'columns':[
				{'data':'ztrybh'},
				{'data':'xm'},
				{'data':'xbdm'},
				{'data':'sfzh'},
				{'data':'xzzDzmc'},
				{'data':'tpsj'},
				{'data':'zbrXm'},
				{'data':'zbrLxdh'}
			]
	};
	$('#DetailTable').DataTable().destroy();
	$("#DetailTable").dataTable($.extend({}, tableOptions, tableDefaultOptions));
}

$(function(){
	if(location.href.indexOf("/list") > -1 ){
		queryEvent();
	}
});