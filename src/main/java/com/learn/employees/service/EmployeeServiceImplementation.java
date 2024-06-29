package com.learn.employees.service;

import com.learn.employees.bean.EmployeeBean;
import com.learn.employees.bean.SearchCriteria;
import com.learn.employees.bean.SearchOperation;
import com.learn.employees.bean.WeatherApiBean;
import com.learn.employees.dao.DepartmentDao;
import com.learn.employees.dao.EmployeeDao;
import com.learn.employees.entity.EmployeeEntity;
import com.learn.employees.exception.NoEmployeeFoundException;
import com.learn.employees.exception.ResourceNotFoundException;
import com.learn.employees.helper.EmpDeptSpec;
import com.learn.employees.helper.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private WebUtils webUtils;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final RestTemplate restTemplate = new RestTemplate();

//    public String addEmployee(EmployeeBean employeeBean) {
//        EmployeeEntity employeeEntity = new EmployeeEntity(12, 12l, "Jaideep", "Role");
//        BeanUtils.copyProperties(employeeBean, employeeEntity);
//        employeeDao.save(employeeEntity);
//        System.out.println("Employee Added");
//        return "Employee Added";
//    }

    @Override
    public List<EmployeeBean> showAllEmployees() {

        ArrayList<EmployeeBean> employeeList = new ArrayList<>();
        List<EmployeeEntity> it = employeeDao.findAll();
        it.forEach(entity -> {
            EmployeeBean employeeBean = new EmployeeBean();
            BeanUtils.copyProperties(entity, employeeBean);
            if (employeeBean.getEmployeeStatus().equalsIgnoreCase("active")) {
                employeeList.add(employeeBean);
            }
        });

        return employeeList;
    }

    @Override
    public List<EmployeeBean> showEmployeeById(int id) {
        EmpDeptSpec empDeptSpec = new EmpDeptSpec();
        List<EmployeeBean> employeeList = new ArrayList<>();
//        Iterable<EmployeeEntity> iterable = employeeDao.findAllById(Collections.singleton(id));
        empDeptSpec.add(new SearchCriteria("employeeId", id, SearchOperation.EQUAL));
        Iterable<EmployeeEntity> iterable = employeeDao.findAll(empDeptSpec);
        iterable.forEach(employeeEntity -> {
            if (employeeEntity.getEmployeeStatus().equalsIgnoreCase("active")) {
                EmployeeBean employeeBean = new EmployeeBean();
                BeanUtils.copyProperties(employeeEntity, employeeBean);
                employeeList.add(employeeBean);
            }
        });
        return employeeList;
    }

    @Override
    public List<EmployeeBean> showEmployeeBySalary(int salary) {
        EmpDeptSpec empDeptSpec = new EmpDeptSpec();
        List<EmployeeBean> employeeList = new ArrayList<>();
//        Iterable<EmployeeEntity> iterable = employeeDao.findEmployeeBySalary(salary);
        empDeptSpec.add(new SearchCriteria("employeeSalary", salary, SearchOperation.EQUAL));
        Iterable<EmployeeEntity> iterable = employeeDao.findAll(empDeptSpec);
        iterable.forEach(employeeEntity -> {
            if (employeeEntity.getEmployeeStatus().equalsIgnoreCase("active")) {
                EmployeeBean employeeBean = new EmployeeBean();
                BeanUtils.copyProperties(employeeEntity, employeeBean);
                employeeList.add(employeeBean);
            }
        });
        return employeeList;
    }

    @Override
    public String updateEmployeeById(int id, EmployeeBean employeeBean) {
        String msg = null;
        try {
            Optional<EmployeeEntity> employeeEntity = employeeDao.findById(id);
            msg = "";
            if (employeeEntity.isPresent()) {
                EmployeeEntity employee = employeeEntity.get();
                if (employee.getEmployeeStatus().equalsIgnoreCase("active")) {
                    employee.setEmployeeSalary(employeeBean.getEmployeeSalary());
                    employee.setEmployeeMobile(employeeBean.getEmployeeMobile());
                    employee.setEmployeeRole(employeeBean.getEmployeeRole());
                    employee.setEmployeeName(employeeBean.getEmployeeName());
                    employeeDao.save(employee);
                    msg = "Employee with id " + id + " has been updated";
                } else {
                    msg = "No such employee found(Deleted)";
                }
            } else {
                msg = "No such employee found";

            }
        } catch (NullPointerException e) {
            msg = "No such employee found(Null Pointer)";
        }
        return msg;
    }

    public String deleteEmployeeById(int id) throws NoEmployeeFoundException {
        String msg = null;
        EmpDeptSpec empDeptSpec = new EmpDeptSpec();
        try {
//            Optional<EmployeeEntity> employeeEntity = employeeDao.findById(id);
            empDeptSpec.add(new SearchCriteria("id", id, SearchOperation.EQUAL));
            Optional<EmployeeEntity> employeeEntity = employeeDao.findOne(empDeptSpec);
            msg = "";
            if (employeeEntity.isPresent()) {
                EmployeeEntity employee = employeeEntity.get();
                if (employee.getEmployeeStatus().equalsIgnoreCase("active")) {
                    employee.setEmployeeStatus("inactive");
                    employeeDao.save(employee);
                    msg = "Successfully deleted";
                } else msg = "No such employee found(Deleted)";
            } else msg = "No such employee found";
        } catch (NullPointerException e) {
            msg = "No such employee found(Null Pointer)";
        }
        return msg;
    }

    @Override
    public List<EmployeeBean> findEmployeeLikeName(String name) {
        EmpDeptSpec empDeptSpec = new EmpDeptSpec();
//        List<EmployeeEntity> employeeEntities=employeeDao.findAll(empDeptSpec.nameLike(name));
        empDeptSpec.add(new SearchCriteria("employeeName", name, SearchOperation.MATCH_IGNORE_CASE));
        empDeptSpec.add(new SearchCriteria("employeeStatus", "Active", SearchOperation.MATCH_CASE));
        List<EmployeeEntity> employeeEntities = employeeDao.findAll(empDeptSpec);
        List<EmployeeBean> employeeBeans = new ArrayList<>();
        employeeEntities.forEach(entity -> {
            EmployeeBean employeeBean = new EmployeeBean();
            BeanUtils.copyProperties(entity, employeeBean);
            employeeBeans.add(employeeBean);
        });
        return employeeBeans;
    }

    @Override
    public WeatherApiBean getWeatherDetailsByEmpIdAndIpaddress(int empId, HttpHeaders headers, HttpServletRequest httpServletRequest) throws ResourceNotFoundException {
        webUtils.setRequest(httpServletRequest);
        String Ipaddress = webUtils.getClientIp();
        try {
            String city = "Pune";
            EmpDeptSpec empDeptSpec = new EmpDeptSpec();
            empDeptSpec.add(new SearchCriteria("employeeId",empId,SearchOperation.EQUAL));
            empDeptSpec.add(new SearchCriteria("employeeStatus","Active",SearchOperation.EQUAL));
            Optional<EmployeeEntity> employeeEntityOptional = employeeDao.findOne(empDeptSpec);
            if(employeeEntityOptional.isPresent()) {
                if (!discoveryClient.getInstances("WEATHER-SERVICE").isEmpty()) {
                    String hostUri = String.valueOf(discoveryClient.getInstances("WEATHER-SERVICE").get(0).getUri());
                    URI uri = UriComponentsBuilder
                            .fromUriString(hostUri + "/employee").pathSegment(city)
                            .queryParam("empId", empId).build().toUri();
                    ResponseEntity<WeatherApiBean> response = restTemplate.exchange(uri, HttpMethod.GET, null, WeatherApiBean.class);
                    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                        return response.getBody();
                    }
                } else {
                    throw new ResourceNotFoundException("Service unavailable");
                }
            } else {
                throw new NoEmployeeFoundException("No such employee available");
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Service unavailable");
        }
        return new WeatherApiBean();
    }
}
