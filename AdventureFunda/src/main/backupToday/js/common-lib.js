function pagination(tableName,itemsPerPage,navPosition){
	$('#pageNavPosition').empty();
	// Instantiate pagination after data is available  
	pager = new Pager(tableName, itemsPerPage);
	pager.init();
	pager.showPageNav("pager", navPosition);
	pager.showPage(1);
	      
	// pagination object codes.
	function Pager(tableName, itemsPerPage) {
	    this.tableName = tableName;
	    this.itemsPerPage = itemsPerPage;
	    this.currentPage = 1;
	    this.pages = 0;
	    this.inited = false;

	    this.showRecords = function (from, to) {
	        var rows = document.getElementById(tableName).rows;
	        // i starts from 1 to skip table header row
	        for (var i = 1; i < rows.length; i++) {
	            if (i < from || i > to){
	            	rows[i].style.display = "none";
	            }
	            else 
	            	{
	            		rows[i].style.display = '';
	            	}
	        }
	    }

	    this.showPage = function (pageNumber) {
	        if (!this.inited) {
	            alert("not inited");
	            return;
	        }

	        var oldPageAnchor = document.getElementById("pg" + this.currentPage);
	        oldPageAnchor.className = "pg-normal";

	        this.currentPage = pageNumber;
	        var newPageAnchor = document.getElementById("pg" + this.currentPage);
	        newPageAnchor.className = "pg-selected";

	        var from = (pageNumber - 1) * itemsPerPage + 1;
	        var to = from + itemsPerPage - 1;
	        this.showRecords(from, to);
	    }

	    this.prev = function () {
	        if (this.currentPage > 1){
	        	this.showPage(this.currentPage - 1);
	        }
	    }

	    this.next = function () {
	        if (this.currentPage < this.pages) {
	            this.showPage(this.currentPage + 1);
	        }
	    }

	    this.init = function () {
	        var rows = document.getElementById(tableName).rows;
	        var records = (rows.length - 1);
	        this.pages = Math.ceil(records / itemsPerPage);
	        this.inited = true;
	    }
	    
	    this.showPageNav = function (pagerName, positionId) {
	        if (!this.inited) {
	            alert("not inited");
	            return;
	        }
	       // var element = document.getElementById(positionId);
	        var pagerHtml = '<li><a onclick="' + pagerName + '.prev();" class="pg-normal" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
	        for (var page = 1; page <= this.pages; page++){
	            pagerHtml += '<li><a id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</a></li>';
	        }
	        pagerHtml += '<li><a onclick="' + pagerName + '.next();" class="pg-normal" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>';
	        $('#pageNavPosition').append(pagerHtml);
	    }
	}
}


function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}
    
function highLightMenu() {
	setTimeout(function(){ 
		var entityName = getUrlParameter('entity');
		var entityOp = getUrlParameter('entity-op');
		
		$('li[data-entity='+entityName+']').find('ul').addClass('collapse in');
		$('li[data-entity-op='+entityOp+']').css('background-color','#bfbfbf');
	}, 200);
}


function getBaseUrl() {
	var url = window.location.origin + '/';
	
	var path = location.pathname.split('/');
	
	url += path[1] 
	
	return url;
}

$('body').on('change', '.file-upload', function() {
	
	var entityType = $(this).attr("data-entityType");
	var uploadFor =  $(this).attr("data-uploadFor");
	
	var url = window.location.origin + '/';
	
	var path = location.pathname.split('/');
	
	url += path[1] + '/ems/applicationService/uploadFile';
	
	var formData = new FormData();
	
    formData.append("file", $(this)[0].files);
    formData.append("entityType", entityType);
    formData.append("uploadFor", uploadFor);
    
    $.ajax({
	  url: url,
	  headers: {ContentType: multipart/form-data},
	  processData: false,
	  contentType: false,
	  method: "POST",
	  dataType: "json",
	  data: formData
	}).done(function(response) {
		console.log(response);
//		$('.messageBlock').text(response.messageDesc);
//		callback("success", response);
	}).fail(function(error) {
	    console.log( "error" );
//	    callback("error", error);
	});

});