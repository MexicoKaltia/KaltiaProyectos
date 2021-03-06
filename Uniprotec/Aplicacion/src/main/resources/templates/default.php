<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>UNIPROTECT V 1.4</title>
    <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="description" content="This is an example dashboard created using build-in elements and components.">
    <meta name="msapplication-tap-highlight" content="no">

    <link href="./main.css" rel="stylesheet">
</head>

<body>

    
    <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
        <div class="app-header header-shadow">
            <div class="app-header__logo">
                <div class="logo-src"></div>
                <div class="header__pane ml-auto">
                    <div>
                        <button type="button" class="hamburger close-sidebar-btn hamburger--elastic"
                            data-class="closed-sidebar">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="app-header__mobile-menu">
                <div>
                    <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                        <span class="hamburger-box">
                            <span class="hamburger-inner"></span>
                        </span>
                    </button>
                </div>
            </div>
            <div class="app-header__menu">
                <span>
                    <button type="button"
                        class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                        <span class="btn-icon-wrapper">
                            <i class="fa fa-ellipsis-v fa-w-6"></i>
                        </span>
                    </button>
                </span>
            </div>
            <div class="app-header__content">
                <div class="app-header-left">
                    <div class="search-wrapper">
                        <div class="input-holder">
                            <input type="text" class="search-input" placeholder="Type to search">
                            <button class="search-icon"><span></span></button>
                        </div>
                        <button class="close"></button>
                    </div>
                    <ul class="header-menu nav">
                        <li class="nav-item">
                            <a href="javascript:void(0);" class="nav-link">
                                <i class="nav-link-icon fa fa-database"> </i>
                                Statistics
                            </a>
                        </li>
                        <li class="btn-group nav-item">
                            <a href="javascript:void(0);" class="nav-link">
                                <i class="nav-link-icon fa fa-edit"></i>
                                Projects
                            </a>
                        </li>
                        <li class="dropdown nav-item">
                            <a href="javascript:void(0);" class="nav-link">
                                <i class="nav-link-icon fa fa-cog"></i>
                                Settings
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="app-header-right">
                    <div class="header-btn-lg pr-0">
                        <div class="widget-content p-0">
                            <div class="widget-content-wrapper">
                                <div class="widget-content-left">
                                    <div class="btn-group">
                                        <a data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                            class="p-0 btn">
                                            <img width="42" class="rounded-circle" src="assets/images/avatars/1.jpg"
                                                alt="">
                                            <i class="fa fa-angle-down ml-2 opacity-8"></i>
                                        </a>
                                        <div tabindex="-1" role="menu" aria-hidden="true"
                                            class="dropdown-menu dropdown-menu-right">
                                            <button type="button" tabindex="0" class="dropdown-item">User
                                                Account</button>
                                            <button type="button" tabindex="0" class="dropdown-item">Settings</button>
                                            <h6 tabindex="-1" class="dropdown-header">Header</h6>
                                            <button type="button" tabindex="0" class="dropdown-item">Actions</button>
                                            <div tabindex="-1" class="dropdown-divider"></div>
                                            <button type="button" tabindex="0" class="dropdown-item">Dividers</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content-left  ml-3 header-user-info">
                                    <div class="widget-heading">
                                        Alina Mclourd
                                    </div>
                                    <div class="widget-subheading">
                                        VP People Manager
                                    </div>
                                </div>
                                <div class="widget-content-right header-user-info ml-3">
                                    <button type="button"
                                        class="btn-shadow p-1 btn btn-primary btn-sm show-toastr-example">
                                        <i class="fa text-white fa-calendar pr-1 pl-1"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="app-main">
            <div class="app-sidebar sidebar-shadow">
                <div class="app-header__logo">
                    <div class="logo-src"></div>
                    <div class="header__pane ml-auto">
                        <div>
                            <button type="button" class="hamburger close-sidebar-btn hamburger--elastic"
                                data-class="closed-sidebar">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="app-header__mobile-menu">
                    <div>
                        <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
                <div class="app-header__menu">
                    <span>
                        <button type="button"
                            class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                            <span class="btn-icon-wrapper">
                                <i class="fa fa-ellipsis-v fa-w-6"></i>
                            </span>
                        </button>
                    </span>
                </div>
                <div class="scrollbar-sidebar">  
                    <div class="app-sidebar__inner">
                        <ul class="vertical-nav-menu">
                            <li class="app-sidebar__heading">PRINCIPAL</li>
                            <li>
                                <a href="index.html" class="mm-active">
                                    <i class="metismenu-icon pe-7s-rocket"></i>
                                    CONTROL
                                </a>
                            </li>
                            <li class="app-sidebar__heading">PRODUCTOS</li>
                            <li>
                                <a href="#">
                                    <i class="metismenu-icon pe-7s-diamond"></i>
                                    CURSOS
                                    <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
                                </a>
                                <ul>
                                    <li>
                                        <a href="form-ACurso.html">
                                            <i class="metismenu-icon"></i>
                                            Alta Nuevo Curso.
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon">
                                            </i>Edicion Curso.
                                        </a>
                                    </li>
                                    <li>
                                        <a href="https://www.uniprotec.net/cursos.html">
                                            <i class="metismenu-icon">
                                            </i>Consulta Curso.
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="app-sidebar__heading">INSTRUCTORES</li>
                            <li>
                                <a href="#">
                                    <i class="metismenu-icon pe-7s-diamond"></i>
                                    PERSONAL
                                    <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
                                </a>
                                <ul>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon"></i>
                                            Alta Nuevo Instructor.
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon">
                                            </i>Edicion Instructor
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon">
                                            </i>Consulta Instructor
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon">
                                            </i>Nueva Asignacion Instructor - Curso
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon">
                                            </i>Consulta Edicion Asignaciones
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="app-sidebar__heading">CLIENTES</li>
                            <li>
                                <a href="#">
                                    <i class="metismenu-icon pe-7s-diamond"></i>
                                    CATALOGO
                                    <i class="metismenu-state-icon pe-7s-angle-down caret-left"></i>
                                </a>
                                <ul>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon pe-7s-mouse">
                                            </i>Alta Cliente
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon pe-7s-eyedropper">
                                            </i>Edicion Consulta Cliente
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="metismenu-icon pe-7s-pendrive">
                                            </i>Consultas Regiones
                                        </a>
                                    </li>
                                </ul>
                            </li>
            
                            <li class="app-sidebar__heading">ACCIONES</li>
                            <li>
                                <a href="#">
                                    <i class="metismenu-icon pe-7s-mouse">
                                    </i>NUEVA ASIGNACION
                                </a>
                            </li>
                            <li>
                                <a href="">
                                    <i class="metismenu-icon pe-7s-eyedropper">
                                    </i>CURSOS AGENDADOS
                                </a>
                            </li>
                            <li>
                                <a href="">
                                    <i class="metismenu-icon pe-7s-pendrive">
                                    </i>CONSULTAS
                                </a>
                            </li>
            
                        </ul>
                    </div>
                </div>  
            </div> 

             <div class="app-main__outer">
                <div class="app-main__inner">
                    <div class="app-page-title">
                        <div class="page-title-wrapper">
                            <div class="page-title-heading">
                                <div class="page-title-icon">
                                    <i class="pe-7s-news-paper icon-gradient bg-mean-fruit">
                                    </i>
                                </div>
                                <div>TABLERO DE CONTROL
                                    <div class="page-title-subheading">PANTALLA PRINCIPAL CON LOS INDICADORES DE
                                        SERVICIO.
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-xl-4">
                            <div class="card mb-3 widget-content bg-midnight-bloom">
                                <div class="widget-content-wrapper text-white">
                                    <div class="widget-content-left">
                                        <div class="widget-heading">TOTAL ORDENES</div>
                                        <div class="widget-subheading">ORDENES DE SERVICIO</div>
                                    </div>
                                    <div class="widget-content-right">
                                        <div class="widget-numbers text-white"><span>186</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-4">
                            <div class="card mb-3 widget-content bg-arielle-smile">
                                <div class="widget-content-wrapper text-white">
                                    <div class="widget-content-left">
                                        <div class="widget-heading">CLIENTES</div>
                                        <div class="widget-subheading">DETALLES CLIENTES ACTIVOS</div>
                                    </div>
                                    <div class="widget-content-right">
                                        <div class="widget-numbers text-white"><span>56</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-4">
                            <div class="card mb-3 widget-content bg-grow-early">
                                <div class="widget-content-wrapper text-white">
                                    <div class="widget-content-left">
                                        <div class="widget-heading">ORDENES PENDIENTES</div>
                                        <div class="widget-subheading">CURSOS AGENDADOS</div>
                                    </div>
                                    <div class="widget-content-right">
                                        <div class="widget-numbers text-white"><span>46</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-6">
                            <div class="mb-3 card">
                                <div class="card-header-tab card-header-tab-animation card-header">
                                    <div class="card-header-title">
                                        <i class="header-icon lnr-apartment icon-gradient bg-love-kiss"> </i>
                                        VENTAS
                                    </div>
                                    <ul class="nav">
                                        <li class="nav-item"><a href="javascript:void(0);"
                                                class="active nav-link">ASIGNACION</a></li>
                                        <li class="nav-item"><a href="javascript:void(0);"
                                                class="nav-link second-tab-toggle">HISTORIAL</a></li>
                                    </ul>
                                </div>
                                <div class="card-body">
                                    <div class="card mb-3 widget-content bg-midnight-bloom">
                                        <div class="widget-content-wrapper text-white">
                                            <div class="widget-content-left">
                                                <div class="widget-heading">NUEVA ASIGNACION</div>
                                                <div class="widget-subheading">ORDEN DE SERVICIO</div>
                                            </div>
                                            <div class="widget-content-right">
                                                <div class="widget-numbers text-white"><span>+ 1</span></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card mb-3 widget-content bg-arielle-smile">
                                        <div class="widget-content-wrapper text-white">
                                            <div class="widget-content-left">
                                                <div class="widget-heading">CLIENTES</div>
                                                <div class="widget-subheading">CURSOS IMPARTIDOS</div>
                                            </div>
                                            <div class="widget-content-right">
                                                <div class="widget-numbers text-white"><span>568</span></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-6">
                            <div class="mb-3 card">
                                <div class="card-header-tab card-header">
                                    <div class="card-header-title">
                                        <i class="header-icon lnr-rocket icon-gradient bg-tempting-azure"> </i>
                                        REPORTE DE VENTAS
                                    </div>
                                    <div class="btn-actions-pane-right">
                                        <div class="nav">
                                            <a href="javascript:void(0);"
                                                class="border-0 btn-pill btn-wide btn-transition active btn btn-outline-alternate">Tab
                                                1</a>
                                            <a href="javascript:void(0);"
                                                class="ml-1 btn-pill btn-wide border-0 btn-transition  btn btn-outline-alternate second-tab-toggle-alt">Tab
                                                2</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-content">
                                    <div class="tab-pane fade active show" id="tab-eg-55">
                                        <div class="widget-chart p-3">
                                            <div style="height: 350px">
                                                <canvas id="line-chart"></canvas>
                                            </div>
                                            <div class="widget-chart-content text-center mt-5">
                                                <div class="widget-description mt-0 text-warning">
                                                    <i class="fa fa-arrow-left"></i>
                                                    <span class="pl-1">175.5%</span>
                                                    <span class="text-muted opacity-8 pl-1">CRECIEMIENTO DE
                                                        VENTAS</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="pt-2 card-body">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="widget-content">
                                                        <div class="widget-content-outer">
                                                            <div class="widget-content-wrapper">
                                                                <div class="widget-content-left">
                                                                    <div class="widget-numbers fsize-3 text-muted">63%
                                                                    </div>
                                                                </div>
                                                                <div class="widget-content-right">
                                                                    <div class="text-muted opacity-6">CURSOS TECNICOS
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="widget-progress-wrapper mt-1">
                                                                <div
                                                                    class="progress-bar-sm progress-bar-animated-alt progress">
                                                                    <div class="progress-bar bg-danger"
                                                                        role="progressbar" aria-valuenow="63"
                                                                        aria-valuemin="0" aria-valuemax="100"
                                                                        style="width: 63%;"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="widget-content">
                                                        <div class="widget-content-outer">
                                                            <div class="widget-content-wrapper">
                                                                <div class="widget-content-left">
                                                                    <div class="widget-numbers fsize-3 text-muted">32%
                                                                    </div>
                                                                </div>
                                                                <div class="widget-content-right">
                                                                    <div class="text-muted opacity-6">CURSOS SEGURIDAD E
                                                                        HIGIENE</div>
                                                                </div>
                                                            </div>
                                                            <div class="widget-progress-wrapper mt-1">
                                                                <div
                                                                    class="progress-bar-sm progress-bar-animated-alt progress">
                                                                    <div class="progress-bar bg-success"
                                                                        role="progressbar" aria-valuenow="32"
                                                                        aria-valuemin="0" aria-valuemax="100"
                                                                        style="width: 32%;"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="widget-content">
                                                        <div class="widget-content-outer">
                                                            <div class="widget-content-wrapper">
                                                                <div class="widget-content-left">
                                                                    <div class="widget-numbers fsize-3 text-muted">71%
                                                                    </div>
                                                                </div>
                                                                <div class="widget-content-right">
                                                                    <div class="text-muted opacity-6">CURSOS SALUD
                                                                        OCUPACIONAL Y AMBIENTAL</div>
                                                                </div>
                                                            </div>
                                                            <div class="widget-progress-wrapper mt-1">
                                                                <div
                                                                    class="progress-bar-sm progress-bar-animated-alt progress">
                                                                    <div class="progress-bar bg-primary"
                                                                        role="progressbar" aria-valuenow="71"
                                                                        aria-valuemin="0" aria-valuemax="100"
                                                                        style="width: 71%;"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="widget-content">
                                                        <div class="widget-content-outer">
                                                            <div class="widget-content-wrapper">
                                                                <div class="widget-content-left">
                                                                    <div class="widget-numbers fsize-3 text-muted">41%
                                                                    </div>
                                                                </div>
                                                                <div class="widget-content-right">
                                                                    <div class="text-muted opacity-6">CURSOS
                                                                        ESPECIALIZADOS</div>
                                                                </div>
                                                            </div>
                                                            <div class="widget-progress-wrapper mt-1">
                                                                <div
                                                                    class="progress-bar-sm progress-bar-animated-alt progress">
                                                                    <div class="progress-bar bg-warning"
                                                                        role="progressbar" aria-valuenow="41"
                                                                        aria-valuemin="0" aria-valuemax="100"
                                                                        style="width: 41%;"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-xl-4">
                            <div class="card mb-3 widget-content">
                                <div class="widget-content-outer">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left">
                                            <div class="widget-heading">ASIGNACIONES</div>
                                            <div class="widget-subheading">TOTAL ASIGNACIONES</div>
                                        </div>
                                        <div class="widget-content-right">
                                            <div class="widget-numbers text-success">186</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-4">
                            <div class="card mb-3 widget-content">
                                <div class="widget-content-outer">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left">
                                            <div class="widget-heading">CANCELACIONES</div>
                                            <div class="widget-subheading">ANUAL</div>
                                        </div>
                                        <div class="widget-content-right">
                                            <div class="widget-numbers text-warning">3</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xl-4">
                            <div class="card mb-3 widget-content">
                                <div class="widget-content-outer">
                                    <div class="widget-content-wrapper">
                                        <div class="widget-content-left">
                                            <div class="widget-heading">DISPONIBILIDAD</div>
                                            <div class="widget-subheading">TOTAL </div>
                                        </div>
                                        <div class="widget-content-right">
                                            <div class="widget-numbers text-danger">45,9%</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="main-card mb-3 card">
                                <div class="card-header">CLIENTES ACTIVOS
                                    <div class="btn-actions-pane-right">
                                        <div role="group" class="btn-group-sm btn-group">
                                            <button class="active btn btn-focus">ACTUAL</button>
                                            <button class="btn btn-focus">HOY</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="align-middle mb-0 table table-borderless table-striped table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-center">#</th>
                                                <th>NOMBRE</th>
                                                <th class="text-center">CEDE</th>
                                                <th class="text-center">ESTATUS</th>
                                                <th class="text-center">DETALLE</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="text-center text-muted">#345</td>
                                                <td>
                                                    <div class="widget-content p-0">
                                                        <div class="widget-content-wrapper">
                                                            <div class="widget-content-left mr-3">
                                                                <div class="widget-content-left">
                                                                    <img width="40" class="rounded-circle"
                                                                        src="assets/images/avatars/4.jpg" alt="">
                                                                </div>
                                                            </div>
                                                            <div class="widget-content-left flex2">
                                                                <div class="widget-heading">John Doe</div>
                                                                <div class="widget-subheading opacity-7">QUERETARO</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="text-center">CLIENTE A</td>
                                                <td class="text-center">
                                                    <div class="badge badge-warning">PENDIENTE</div>
                                                </td>
                                                <td class="text-center">
                                                    <button type="button" id="PopoverCustomT-1"
                                                        class="btn btn-primary btn-sm">DETALLE</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center text-muted">#347</td>
                                                <td>
                                                    <div class="widget-content p-0">
                                                        <div class="widget-content-wrapper">
                                                            <div class="widget-content-left mr-3">
                                                                <div class="widget-content-left">
                                                                    <img width="40" class="rounded-circle"
                                                                        src="assets/images/avatars/3.jpg" alt="">
                                                                </div>
                                                            </div>
                                                            <div class="widget-content-left flex2">
                                                                <div class="widget-heading">Ruben Tillman</div>
                                                                <div class="widget-subheading opacity-7">...</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="text-center">CDMX</td>
                                                <td class="text-center">
                                                    <div class="badge badge-success">Completo</div>
                                                </td>
                                                <td class="text-center">
                                                    <button type="button" id="PopoverCustomT-2"
                                                        class="btn btn-primary btn-sm">DETALLE</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center text-muted">#321</td>
                                                <td>
                                                    <div class="widget-content p-0">
                                                        <div class="widget-content-wrapper">
                                                            <div class="widget-content-left mr-3">
                                                                <div class="widget-content-left">
                                                                    <img width="40" class="rounded-circle"
                                                                        src="assets/images/avatars/2.jpg" alt="">
                                                                </div>
                                                            </div>
                                                            <div class="widget-content-left flex2">
                                                                <div class="widget-heading">Elliot Huber</div>
                                                                <div class="widget-subheading opacity-7">----</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="text-center">TOLUCA</td>
                                                <td class="text-center">
                                                    <div class="badge badge-danger">NO ASIGNADO</div>
                                                </td>
                                                <td class="text-center">
                                                    <button type="button" id="PopoverCustomT-3"
                                                        class="btn btn-primary btn-sm">DETALLE</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="text-center text-muted">#55</td>
                                                <td>
                                                    <div class="widget-content p-0">
                                                        <div class="widget-content-wrapper">
                                                            <div class="widget-content-left mr-3">
                                                                <div class="widget-content-left">
                                                                    <img width="40" class="rounded-circle"
                                                                        src="assets/images/avatars/1.jpg" alt=""></div>
                                                            </div>
                                                            <div class="widget-content-left flex2">
                                                                <div class="widget-heading">Vinnie Wagstaff</div>
                                                                <div class="widget-subheading opacity-7">-.,-.,</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="text-center">SITIO</td>
                                                <td class="text-center">
                                                    <div class="badge badge-info">EN ESPERA</div>
                                                </td>
                                                <td class="text-center">
                                                    <button type="button" id="PopoverCustomT-4"
                                                        class="btn btn-primary btn-sm">DETALLE</button>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                                       <!-- Footer-->
                                       <div class="app-wrapper-footer">
                                        <div class="app-footer">
                                            <div class="app-footer__inner">
                                                <div class="app-footer-left">
                                                    <ul class="nav">
                                                        <li class="nav-item">
                                                            <a href="javascript:void(0);" class="nav-link">
                                                                Footer Link 1
                                                            </a>
                                                        </li>
                                                        <li class="nav-item">
                                                            <a href="javascript:void(0);" class="nav-link">
                                                                Footer Link 2
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="app-footer-right">
                                                    <ul class="nav">
                                                        <li class="nav-item">
                                                            <a href="javascript:void(0);" class="nav-link">
                                                                Footer Link 3
                                                            </a>
                                                        </li>
                                                        <li class="nav-item">
                                                            <a href="javascript:void(0);" class="nav-link">
                                                                <div class="badge badge-success mr-1 ml-0">
                                                                    <small>NEW</small>
                                                                </div>
                                                                Footer Link 4
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div></div></div>
                                    
                                <!-- Fin Footer-->
                
            </div>
        </div>  <!-- fin de div principal-->
</div>
    <script type="text/javascript" src="./assets/scripts/main.js"></script>
    <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
</body>

</html>