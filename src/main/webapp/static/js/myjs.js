$(function() {

	// code for jquery dataTable
	var $table = $('#requestList');

	// execute the below code only where we have this table
	if ($table.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';
		jsonUrl = window.contextRoot + '/admin/json/data/requests';

		/* alert(jsonUrl); */
		$table.DataTable({

			lengthMenu : [ [ 15, 20, 50, -1 ],
					[ '15 Records', '20 Records', '50 Records', 'ALL' ] ],
			pageLength : 15,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [
					{
						data : 'id'
					},
					{
						data : 'unit.equipmentName'
					},
					{
						data : 'unit.modelNo'
					},
					{
						data : 'date'
					},
					{
						data : 'requestBy.fullName'
					},
					{
						data : 'descriptionOfMalfunction'
					},
					{

						data : 'recommendedBy.fullName'

					},
					{
						data : 'recommendation'

					},
					{
						data : 'id',
						bSortable : false,
						mRender : function(data, type, row) {
							var str = '';
							if (userRole === 'ADMIN') {
								str += '<a href="' + window.contextRoot
										+ '/admin/edit-request-' + data
										+ '" class="">edit</a>&nbsp;';
								str += '&nbsp;<a href="' + window.contextRoot
										+ '/admin/delete-request-' + data
										+ '" class="">delete</a>';
							}
							return str;
						}

					}

			]
		});
	}

	// code for jquery dataTable
	var $tableUser = $('#userList');

	// execute the below code only where we have this table
	if ($tableUser.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';
		jsonUrl = window.contextRoot + '/admin/json/data/users';

		/* alert(jsonUrl); */
		$tableUser.DataTable({

			lengthMenu : [ [ 15, 20, 50, -1 ],
					[ '15 Records', '20 Records', '50 Records', 'ALL' ] ],
			pageLength : 15,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [

					{
						data : 'firstName'
					},
					{
						data : 'lastName'
					},
					{
						data : 'ssoId'
					},
					{
						data : 'position'
					},
					{
						data : 'division'
					},
					{
						data : 'id',
						bSortable : false,
						mRender : function(data, type, row) {
							var str = '';
							if (userRole === 'ADMIN') {
								str += '<a href="' + window.contextRoot
										+ '/admin/edit-user-' + data
										+ '" class="">edit</a>&nbsp;';
								str += '&nbsp;<a href="' + window.contextRoot
										+ '/admin/delete-person-' + data
										+ '" class="">delete</a>';
							}
							return str;

						}

					}

			]
		});
	}

	// code for jquery dataTable
	var $tableUnit = $('#unitList');

	// execute the below code only where we have this table
	if ($tableUnit.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';
		jsonUrl = window.contextRoot + '/admin/json/data/units';

		/* alert(jsonUrl); */
		$tableUnit.DataTable({

			lengthMenu : [ [ 15, 20, 50, -1 ],
					[ '15 Records', '20 Records', '50 Records', 'ALL' ] ],
			pageLength : 15,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [

					{
						data : 'equipmentName'
					},
					{
						data : 'modelNo'
					},
					{
						data : 'serialNo'
					},
					{
						data : 'otherComponent'
					},
					{
						data : 'receivedBy.fullName'
					},
					{
						data : 'id',
						bSortable : false,
						mRender : function(data, type, row) {
							var str = '';
							if (userRole === 'ADMIN') {
								str += '<a href="' + window.contextRoot
										+ '/admin/edit-unit-' + data
										+ '" class="">edit</a>&nbsp;';
								str += '&nbsp;<a href="' + window.contextRoot
										+ '/admin/delete-unit-' + data
										+ '" class="">delete</a>';
							}
							return str;
						}

					}

			]
		});
	}

	// code for jquery dataTable
	var $tableEquipmentMaintenanceList = $('#equipmentMaintenanceList');

	// execute the below code only where we have this table
	if ($tableEquipmentMaintenanceList.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';
		jsonUrl = window.contextRoot + '/admin/json/data/units';

		/* alert(jsonUrl); */
		$tableEquipmentMaintenanceList.DataTable({

			lengthMenu : [ [ 15, 20, 50, -1 ],
					[ '15 Records', '20 Records', '50 Records', 'ALL' ] ],
			pageLength : 15,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [

					{
						data : 'equipmentName'
					},
					{
						data : 'modelNo'
					},
					{
						data : 'serialNo'
					},
					{
						data : 'otherComponent'
					},

					{
						data : 'id',
						bSortable : false,
						mRender : function(data, type, row) {
							var str = '';
							str += '<a href="' + window.contextRoot
									+ '/admin/view-maintenance-unit-' + data
									+ '" class="">View</a>&nbsp;';
							return str;
						}

					}

			]
		});
	}

	// code for jquery dataTable
	var $tableEquipmentMaintenanceUnitList = $('#equipmentMaintenanceUnitList');

	// execute the below code only where we have this table
	if ($tableEquipmentMaintenanceUnitList.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';

		if (unitId != '')
			jsonUrl = window.contextRoot + '/admin/json/data/' + unitId
					+ '/equipmentMaintenances';

		/* alert(jsonUrl); */
		$tableEquipmentMaintenanceUnitList.DataTable({

			lengthMenu : [ [ 15, 20, 50, -1 ],
					[ '15 Records', '20 Records', '50 Records', 'ALL' ] ],
			pageLength : 15,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [ {
				data : 'request.id'
			}, {
				data : 'request.date'
			}, {
				data : 'operationPerformed'
			}, {
				data : 'date'
			}, {
				data : 'performedBy'
			}, {
				data : 'remark'
			}, {
				data : 'id',
				bSortable : false,
				mRender : function(data, type, row) {
					var str = '';
					if (userRole === 'ADMIN') {
						str += '<a href="' + window.contextRoot
								+ '/admin/edit-equipmentmaintenance-' + data
								+ '" class="">edit</a>&nbsp;';
						str += '&nbsp;<a href="' + window.contextRoot
								+ '/admin/delete-equipmentmaintenance-' + data
								+ '" class="">delete</a>';
					}
					return str;
				}

			}

			]
		});
	}

});