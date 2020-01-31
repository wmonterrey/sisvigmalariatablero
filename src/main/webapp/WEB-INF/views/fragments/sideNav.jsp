<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<!-- Layout sidenav -->
<div id="layout-sidenav" class="layout-sidenav sidenav sidenav-vertical bg-dark">
	<!-- Brand demo (see assets/css/demo/demo.css) -->
    <div class="app-brand demo">
		<span class="app-brand-logo demo bg-primary">
        	<strong><font color="black">S</font></strong>
    	</span>
    	<a href="<spring:url value="/" htmlEscape="true "/>" class="app-brand-text demo sidenav-text font-weight-normal ml-2"><spring:message code="title" /></a>
    	<a href="javascript:void(0)" class="layout-sidenav-toggle sidenav-link text-large ml-auto">
        	<i class="ion ion-md-menu align-middle"></i>
      	</a>
    </div>
    
    <div class="sidenav-divider mt-0"></div>
    
	<!-- Links -->
	<ul class="sidenav-inner py-1">
		<li class="sidenav-item tableros">
            <a href="javascript:void(0)" class="sidenav-link sidenav-toggle"><i class="sidenav-icon ion ion-md-speedometer"></i>
              <div><spring:message code="dashboards" /></div>
            </a>
            <ul class="sidenav-menu">
              <li class="sidenav-item portada">
                <a href="<spring:url value="/" htmlEscape="true "/>" class="sidenav-link">
                  <div><spring:message code="front" /></div>
                </a>
              </li>
              <li class="sidenav-item epid">
                <a href="<spring:url value="/dashboard/epid/" htmlEscape="true "/>" class="sidenav-link">
                  <div><spring:message code="epid" /></div>
                </a>
              </li>
              <li class="sidenav-item vig">
                <a href="<spring:url value="/dashboard/vig/" htmlEscape="true "/>" class="sidenav-link">
                  <div><spring:message code="surveillance" /></div>
                </a>
              </li>
              <li class="sidenav-item mgmt">
                <a href="<spring:url value="/dashboard/casemgm/" htmlEscape="true "/>" class="sidenav-link">
                  <div><spring:message code="cases" /></div>
                </a>
              </li>
            </ul>
        </li>
        <li class="sidenav-item export">
          <a href="<spring:url value="/export/" htmlEscape="true" />" class="sidenav-link"><i class="sidenav-icon ion ion-md-grid"></i>
            <div><spring:message code="export" /></div>
          </a>
        </li>
        <li class="sidenav-item map">
          <a href="<spring:url value="/map/" htmlEscape="true" />" class="sidenav-link"><i class="sidenav-icon ion ion-md-map"></i>
            <div><spring:message code="maps" /></div>
          </a>
        </li>
        <li class="sidenav-item usuarios">
            <a href="javascript:void(0)" class="sidenav-link sidenav-toggle"><i class="sidenav-icon ion ion-md-person"></i>
              <div><spring:message code="users" /></div>
            </a>
            <sec:authorize url="/admin/">
            <ul class="sidenav-menu">
              <li class="sidenav-item uadmin">
                <a href="<spring:url value="/admin/users/" htmlEscape="true "/>" class="sidenav-link">
                  <div><spring:message code="admin" /></div>
                </a>
              </li>
              
            </ul>
            </sec:authorize>
        </li>
        <li class="sidenav-item foci">
          <a href="<spring:url value="/foci/" htmlEscape="true" />" class="sidenav-link"><i class="sidenav-icon ion ion-md-cloud-outline"></i>
            <div><spring:message code="foci" /></div>
          </a>
        </li>
        <li class="sidenav-item">
          <a href="<spring:url value="/logout" htmlEscape="true" />" class="sidenav-link"><i class="sidenav-icon ion ion-ios-log-out"></i>
            <div><spring:message code="logout" /></div>
          </a>
        </li>
	</ul>
    
</div>
<!-- / Layout sidenav -->