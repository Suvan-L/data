/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
        //名字为“MyTool”的toolbar（工具栏）的具体设定。只保留以下功能
       config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles'},
		{ name: 'colors' },
		{ name: 'about' }
	];
           
	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';

        //去掉地下的body p标签
        config.removePlugins = 'elementspath'; 
       //界面语言，默认为'en'
       config.language='zh-cn';
       
       //设置宽高
       config.width=800;
       config.height=400;
       
       // 编辑器样式，有三种:'kname(默认)','office2003','v2'
            //config.skin='kname';
            
        //背景颜色
        config.uiColor="#77DDFF";
        
        //工具栏(基础'Basic',全能'Full',自定义)plugins/toolbar/plugin.js
        config.toolbar="Basic";
        config.toolbar="Full";
        
        config.toolbar_Full = [
       ['Source','-','Save','NewPage','Preview','-','Templates'],
       ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
       ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
       ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
       '/',
       ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
        ['Link','Unlink','Anchor'],
       ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
       '/',
        ['Styles','Format','Font','FontSize'],
        ['TextColor','BGColor']
    ];
    
    //工具栏是否可以被收缩(右边有个小三角形)
    config.toolbarCanCollapse = true;
    //工具栏的位置
    config.toolbarLocation = 'top';//可选：bottom
    //工具栏默认是否展开
    config.toolbarStartupExpanded = true;
    // 取消 “拖拽以改变尺寸”功能 plugins/resize/plugin.js
    config.resize_enabled = false;
    //改变大小的最大高度
    config.resize_maxHeight = 3000;
    //改变大小的最大宽度
    config.resize_maxWidth = 3000;
    //改变大小的最小高度
    config.resize_minHeight = 750;
    //改变大小的最小宽度
    config.resize_minWidth = 750;
    // 当提交包含有此编辑器的表单时，是否自动更新元素内的数据
    config.autoUpdateElement = true;
    // 设置是使用绝对目录还是相对目录，为空为相对目录
    config.baseHref = ''
    // 编辑器的z-index值
    config.baseFloatZIndex = 10000;
};
