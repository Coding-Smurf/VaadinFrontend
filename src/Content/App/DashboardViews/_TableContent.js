import React, {useState, useMemo, useRef, useCallback } from 'react';
import { AgGridReact } from 'ag-grid-react'; // React Data Grid Component
import { Block, ClearRounded, ArrowBackRounded } from '@mui/icons-material';
import "ag-grid-community/styles/ag-grid.css"; // Mandatory CSS required by the Data Grid
import "ag-grid-community/styles/ag-theme-quartz.css"; // Optional Theme applied to the Data Grid

import FormLayout from './_FormLayout';

import './../../Css/TableContent.css';



const TableContent = () => {

    const gridRef = useRef();
    const [selectedRowCount, setSelectedRowCount] = useState(0);
    //set to false
    const [exportSelectionMode, setExportSelectionMode] = useState(false);
    const [formView, setFormView] = useState(false);
    const [selectedRowData, setSelectedRowData] = useState(null); // State to hold selected row data

    const [rowData, setRowData] = useState([
        { nombre: "John", "nombre completo": "John Doe", cargo: "CEO", email: "ad@gmail.com,dusatdutya", teléfono: "123456789", medio: "TV", "tipo de medio": "Nacional", temática: "General", "redes sociales": "Facebook", clientes: "Cliente 1", país: "México", comunidad: "Comunidad 1", región: "Región 1" },
        { nombre: "Jane", "nombre completo": "Jane Doe", cargo: "CFO", email: "asd@gmail.com", teléfono: "123456789", medio: "Radio", "tipo de medio": "Local", temática: "Deportes", "redes sociales": "Twitter", clientes: "Cliente 2", país: "México", comunidad: "Comunidad 2", región: "Región 2" },
        { nombre: "John", "nombre completo": "John Doe", cargo: "CEO", email: "asd@gmail.com", teléfono: "123456789", medio: "TV", "tipo de medio": "Nacional", temática: "General", "redes sociales": "Facebook", clientes: "Cliente 1", país: "México", comunidad: "Comunidad 1", región: "Región 1" },
        { nombre: "John", "nombre completo": "John Doe", cargo: "CEO", email: "asd@gmail.com", teléfono: "123456789", medio: "TV", "tipo de medio": "Nacional", temática: "General", "redes sociales": "Facebook", clientes: "Cliente 1", país: "México", comunidad: "Comunidad 1", región: "Región 1" },
        { nombre: "Jane", "nombre completo": "Jane Doe", cargo: "CFO", email: "asd@gmail.com", teléfono: "123456789", medio: "Radio", "tipo de medio": "Local", temática: "Deportes", "redes sociales": "Twitter", clientes: "Cliente 2", país: "México", comunidad: "Comunidad 2", región: "Región 2" },
        { nombre: "John", "nombre completo": "John Doe", cargo: "CEO", email: "asd@gmail.com", teléfono: "123456789", medio: "TV", "tipo de medio": "Nacional", temática: "General", "redes sociales": "Facebook", clientes: "Cliente 1", país: "México", comunidad: "Comunidad 1", región: "Región 1" },
        { nombre: "John", "nombre completo": "John Doe", cargo: "CEO", email: "asd@gmail.com", teléfono: "123456789", medio: "TV", "tipo de medio": "Nacional", temática: "General", "redes sociales": "Facebook", clientes: "Cliente 1", país: "México", comunidad: "Comunidad 1", región: "Región 1" },
        { nombre: "Jane", "nombre completo": "Jane Doe", cargo: "CFO", email: "asd@gmail.com", teléfono: "123456789", medio: "Radio", "tipo de medio": "Local", temática: "Deportes", "redes sociales": "Twitter", clientes: "Cliente 2", país: "México", comunidad: "Comunidad 2", región: "Región 2" },
        { nombre: "John", "nombre completo": "John Doe", cargo: "CEO", email: "asd@gmail.com", teléfono: "123456789", medio: "TV", "tipo de medio": "Nacional", temática: "General", "redes sociales": "Facebook", clientes: "Cliente 1", país: "México", comunidad: "Comunidad 1", región: "Región 1" },
        { nombre: "John", "nombre completo": "John Doe", cargo: "CEO", email: "asd@gmail.com", teléfono: "123456789", medio: "TV", "tipo de medio": "Nacional", temática: "General", "redes sociales": "Facebook", clientes: "Cliente 1", país: "México", comunidad: "Comunidad 1", región: "Región 1" },
        { nombre: "Jane", "nombre completo": "Jane Doe", cargo: "CFO", email: "asd@gmail.com", teléfono: "123456789", medio: "Radio", "tipo de medio": "Local", temática: "Deportes", "redes sociales": "Twitter", clientes: "Cliente 2", país: "México", comunidad: "Comunidad 2", región: "Región 2" },
        { nombre: "John", "nombre completo": "John Doe", cargo: "CEO", email: "asd@gmail.com", teléfono: "123456789", medio: "TV", "tipo de medio": "Nacional", temática: "General", "redes sociales": "Facebook", clientes: "Cliente 1", país: "México", comunidad: "Comunidad 1", región: "Región 1" },
    ]);
      
   // Column Definitions: Defines the columns to be displayed.
   const [colDefs, setColDefs] = useState([
    { field: "nombre", checkboxSelection: exportSelectionMode },
    { field: "nombre completo" },
    { field: "cargo" },
    { field: "email" },
    { field: "teléfono" },
    { field: "medio" },
    { field: "tipo de medio" },
    { field: "temática" },
    { field: "redes sociales" },
    { field: "clientes" },
    { field: "país" },
    { field: "comunidad" },
    { field: "región" }
]);

const defaultColDef = useMemo(() => ({
    filter: 'agTextColumnFilter',
    floatingFilter: true,
    cellClass: 'transparent-background',
    editable: false,
}), []);

const onGridReady = useCallback((params) => {
    gridRef.current = params.api; // Set grid API reference
}, []);

const deselectAllRows = useCallback(() => {
    if (gridRef.current) {
        gridRef.current.deselectAll(); // Clear all selections
        selectionFalse(); // Set to false
    }
}, []);

const onSelectionChanged = useCallback(() => {
    if (gridRef.current) {
        setSelectedRowCount(gridRef.current.getSelectedRows().length); // Update selected row count
    }
}, []);

const updateColumnDefs = (selectionMode) => {
    setColDefs([
        { field: "nombre", checkboxSelection: selectionMode },
        { field: "nombre completo" },
        { field: "cargo" },
        { field: "email" },
        { field: "teléfono" },
        { field: "medio" },
        { field: "tipo de medio" },
        { field: "temática" },
        { field: "redes sociales" },
        { field: "clientes" },
        { field: "país" },
        { field: "comunidad" },
        { field: "región" }
    ]);
};

const selectionTrue = () => {
    setExportSelectionMode(true);
    updateColumnDefs(true);
};

const selectionFalse = () => {
    setExportSelectionMode(false);
    updateColumnDefs(false);
};

const rowClicked = (event) => {
    if (!exportSelectionMode) {
        setSelectedRowData(event.data); // Save selected row data
        setFormView(true); // Switch to form view
    }
};

return (
    <div style={{ height: '100%', width: '100%' }}>
        <div className="tableViewContainer" style={{ display: (formView) ? 'none' : 'flex' }}>
            <div className="topButtons">
                <button
                    className="addButton"
                    disabled={exportSelectionMode}
                    style={{ boxShadow: exportSelectionMode ? 'none' : '0 5px 10px rgba(0, 0, 0, 0.35)' }}
                >
                    Add
                </button>
                <button
                    className="selectButton"
                    onClick={selectionTrue}
                    disabled={exportSelectionMode}
                    style={{ boxShadow: exportSelectionMode ? 'none' : '0 5px 10px rgba(0, 0, 0, 0.35)' }}
                >
                    Select
                </button>
                <button
                    onClick={deselectAllRows}
                    disabled={(selectedRowCount === 0 && !exportSelectionMode) || !exportSelectionMode}
                    className="clearButton"
                    style={{ display: (selectedRowCount === 0 && !exportSelectionMode) || !exportSelectionMode ? 'none' : 'block' }}
                >
                    <ClearRounded />
                </button>
            </div>
            <div className="ag-theme-quartz table">
                <AgGridReact
                    rowData={rowData}
                    columnDefs={colDefs}
                    defaultColDef={defaultColDef}
                    rowSelection="multiple"
                    rowMultiSelectWithClick={exportSelectionMode}
                    onGridReady={onGridReady}
                    onSelectionChanged={onSelectionChanged}
                    onRowClicked={rowClicked}
                />
            </div>
            <div className="bottomButtons">
                <button
                    className="exportFilters"
                    disabled={exportSelectionMode}
                    style={{ display: exportSelectionMode ? 'none' : 'block' }}
                >
                    Export Filters
                </button>
                <button
                    className="exportSelection"
                    disabled={!exportSelectionMode || (exportSelectionMode && selectedRowCount === 0)}
                    style={{ display: (!exportSelectionMode || (exportSelectionMode && selectedRowCount === 0)) ? 'none' : 'block' }}
                >
                    Export Selected
                </button>
                <button
                    className='deleteSelection'
                    disabled={!exportSelectionMode || (exportSelectionMode && selectedRowCount === 0)}
                    style={{ display: (!exportSelectionMode || (exportSelectionMode && selectedRowCount === 0)) ? 'none' : 'block' }}
                >
                    Delete Selected
                </button>
            </div>
        </div>
        <div className="formViewContainer" style={{ display: (!formView) ? 'none' : 'flex' }}>
            <div className="topButtons">
                <button
                    className="addButton"
                    disabled={true}
                    style={{ boxShadow: 'none' }}
                >
                    Add
                </button>
                <button
                    className="selectButton"
                    disabled={true}
                    style={{ boxShadow: 'none' }}
                >
                    Select
                </button>
                <button
                    className="clearButton"
                    onClick={() => {
                        setFormView(false);
                        setSelectedRowData(null); // Clear selected row data when leaving form view
                    }}
                    style={{ display: 'block' }}
                >
                    <ArrowBackRounded />
                </button>
            </div>
            <div className="formLayout">
                {/* Render the form layout with the selected row data */}
                {selectedRowData && (
                    <FormLayout
                        nombre={selectedRowData.nombre}
                        nombreCompleto={selectedRowData["nombre completo"]}
                        cargo={selectedRowData.cargo}
                        email={selectedRowData.email}
                        telefono={selectedRowData.teléfono}
                        medio={selectedRowData.medio}
                        tipoMedio={selectedRowData["tipo de medio"]}
                        tematica={selectedRowData.temática}
                        redesSociales={selectedRowData["redes sociales"]}
                        clientes={selectedRowData.clientes}
                        pais={selectedRowData.país}
                        comunidad={selectedRowData.comunidad}
                        region={selectedRowData.región}
                    />
                )}
            </div>
        </div>
    </div>
);
};

export default TableContent;