
import React, { useContext, useEffect, useState } from 'react'
import { lighten, makeStyles, useTheme } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import Checkbox from '@material-ui/core/Checkbox';
import IconButton from '@material-ui/core/IconButton';
import Tooltip from '@material-ui/core/Tooltip';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Switch from '@material-ui/core/Switch';
import DeleteIcon from '@material-ui/icons/Delete';
import FilterListIcon from '@material-ui/icons/FilterList';
import NavigationAppContext from '../../../stores/NavigationAppContext'
import { NativeSelect } from '@material-ui/core';
import FirstPageIcon from '@material-ui/icons/FirstPage';
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight';
import LastPageIcon from '@material-ui/icons/LastPage';


const useStyleTable = makeStyles((theme) => ({
    paper: {
        width: '100%',
        marginBottom: theme.spacing(2) // 8 * 2 =16px default
    },
    containerTable: {
        maxHeight: '400px'
        // minHeight: '400px'
    },
    paginationCustomize: {
        flexShrink: 0
    }
}))


function HeaderTable(pros) {

    const { ColumnSortArr, TypeSortArr, HandleSortTableArr } = pros;

    const headCells = [
        { id: "idstaff", label: "Id Staff", isNumber: false, disablePadding: false, isSort: true },
        { id: "username", label: "Name Staff", isNumber: false, disablePadding: false, isSort: true },
        { id: "numberroom", label: "Room", isNumber: true, disablePadding: false, isSort: true },
        { id: "typeservices", label: "Type Services", isNumber: false, disablePadding: false, isSort: true },
        { id: "status", label: "Status", isNumber: false, disablePadding: false, isSort: true },
        { id: "detail", label: "Detail", isNumber: false, disablePadding: false, isSort: false }
    ]

    // hàm gọi hàm cú pháp của react nếu ko cso thê sử dụng () => createRequestSort(colum) ở dưới 
    const createRequestSort = (column) => (event) => {
        HandleSortTableArr(column);
    }

    return (
        <TableHead>
            <TableRow>
                {
                    headCells.map((row) => (
                        <TableCell
                            key={row.id}
                            padding={row.disablePadding ? 'none' : 'default'}
                            align={row.isNumber ? 'right' : 'left'}
                        >
                            <TableSortLabel
                                disabled={!row.isSort}
                                active={ColumnSortArr === row.id ? true : false} // view icon arrow
                                direction={ColumnSortArr === row.id ? TypeSortArr : 'asc'} // view direction arrow up or down
                                onClick={createRequestSort(row.id)}
                            >
                                {row.label}
                            </TableSortLabel>
                        </TableCell>
                    ))
                }
            </TableRow>
        </TableHead>
    )
}


function CustomizaPagination(pros) {

    const { count, rowsPerPage, page, onChangePage } = pros;

    const them = useTheme();

    const classes = useStyleTable();

    const maxPage = () => {
        return Math.ceil(count / rowsPerPage) - 1;// tại vì page bắt đầu từ 0 nên trừ cho 1 , ceil đã làm tròn lên rồi 
    }

    const handleClickFirstPage = (event) => {
        onChangePage(event, 0);
    }

    const handleClickPreviousPage = (event) => {
        onChangePage(event, page - 1);
    }

    const handleClickNextPage = (event) => {
        onChangePage(event, page + 1);
    }

    const handleClickLastPage = (event) => {
        onChangePage(event, maxPage);
    }

    return (
        <div className={classes.paginationCustomize}>
            <IconButton
                disabled={page === 0}
                aria-label="first page"
                onClick={handleClickFirstPage}
            >
                {them.direction === 'ltr' ? <FirstPageIcon /> : <LastPageIcon />}
            </IconButton>

            <IconButton
                disabled={page === 0}
                aria-label="previous page"
                onClick={handleClickPreviousPage}
            >
                {them.direction === 'ltr' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
            </IconButton>

            <IconButton
                disabled={page === maxPage()}
                aria-label="next page"
                onClick={handleClickNextPage}
            >
                {them.direction === 'ltr' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
            </IconButton>

            <IconButton
                disabled={page === maxPage()}
                aria-label="Last page"
                onClick={handleClickLastPage}
            >
                {them.direction === 'ltr' ? <LastPageIcon /> : <FirstPageIcon />}
            </IconButton>

        </div>
    )

}

export default function Staffserviceindextable(pros) {

    const { dataFetchIsServer, changeSelectStatusHandler, handlerButtonDetailViewServices } = useContext(NavigationAppContext);

    const [listOriginal, setlistOriginal] = useState([]);
    const [page, setpage] = useState(0);
    const [rowsPerPage, setrowsPerPage] = useState(5);
    const [ColumnSort, setColumnSort] = useState("numberroom");
    const [TypeSort, setTypeSort] = useState("asc");


    useEffect(() => {
        setlistOriginal([...dataFetchIsServer]);
    }, [dataFetchIsServer])

    const classes = useStyleTable();

    // --------------------Pagination -----------------------

    const handleOnChangePage = (argrument1, pageArgment) => {
        setpage(pageArgment);
    }

    const handleOnChangeRowsPerPage = (event) => {
        setrowsPerPage(parseInt(event.target.value, 10));
        setpage(0);
    }

    // --------------------Sort -----------------------

    const HandleSortTable = (column) => {
        const isAscSort = column === ColumnSort && TypeSort === 'asc';
        setTypeSort(isAscSort ? 'desc' : 'asc');
        setColumnSort(column);
    }

    const sortFucntionASC = (a, b) => {
        if (a[ColumnSort] > b[ColumnSort]) {
            return 1
        } else if (a[ColumnSort] < b[ColumnSort]) {
            return -1;
        }
        return 0;
    }

    const coreSort = (a, b) => {
        return TypeSort === 'asc' ? sortFucntionASC(a, b) : -sortFucntionASC(a, b);
    }

    const sortList = (arrays) => {
        const listCopy = arrays.map((el) => el);
        return listCopy.sort((a, b) => {
            return coreSort(a, b);
        });
    }

    return (
        <div>
            <Paper className={classes.paper}>
                <TableContainer className={classes.containerTable}>
                    <Table
                        stickyHeader // integrated with container maxheight 400px
                        size='small'
                        aria-label="sticky table"
                    >
                        <HeaderTable ColumnSortArr={ColumnSort} TypeSortArr={TypeSort} HandleSortTableArr={HandleSortTable} />
                        <TableBody className="group--body-table-viewer-workstaff">
                            {sortList(listOriginal).slice(rowsPerPage * page, rowsPerPage * page + rowsPerPage).map((row) => (
                                <TableRow key={Math.random()}
                                    className={(row.status === "Off" || row.status === "Done") ? "group--body-table-viewer-workstaff-rowDisable" : ""}
                                >
                                    <TableCell align="justify" component="th" scope="row">
                                        {row.idstaff}
                                    </TableCell>
                                    <TableCell align="justify">
                                        {row.username}
                                    </TableCell>
                                    <TableCell align="right">
                                        {row.numberroom}
                                    </TableCell>
                                    <TableCell align="justify" className={row.typeservices}>
                                        {row.typeservices}
                                    </TableCell>
                                    <TableCell align="justify">
                                        {row.status !== "Off" && row.status !== "Done" &&
                                            <NativeSelect
                                                className="select--status--service"
                                                defaultValue={row.status}
                                                onChange={(event) => { changeSelectStatusHandler(row, event) }}
                                                inputProps={{
                                                    name: 'status',
                                                    id: 'age-native-helper',
                                                }}>
                                                <option disabled={row.status === "ToDo" ? true : false} value={"Shipping"} >Shipping</option>
                                                <option value={"ToDo"} >ToDo</option>
                                                <option value={"Done"}>Done</option>
                                            </NativeSelect>
                                        }
                                        {(row.status === "Off" || row.status === "Done") &&
                                            <p>Done</p>
                                        }
                                    </TableCell>
                                    <TableCell align="justify">
                                        {row.status === "ToDo" &&
                                            <button onClick={() => { handlerButtonDetailViewServices(row) }} className="btn--quanlikhachsan btn--quanlikhachsan__green__MoreService" > Detail </button>
                                        }
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    component='div'
                    rowsPerPageOptions={[5, 10, 20, 50, { value: -1, label: "All" }]}
                    page={page}
                    count={listOriginal.length}
                    rowsPerPage={rowsPerPage}
                    onChangePage={handleOnChangePage} // auto set number new page
                    onChangeRowsPerPage={handleOnChangeRowsPerPage}
                    ActionsComponent={CustomizaPagination} // auto truyền các pros vào luôn 
                />
            </Paper>
        </div>
    )
}
