
package com.neegix.organization.dept.interfaces.controller;

import com.neegix.cqrs.command.UniversalCommandBus;
import com.neegix.cqrs.query.UniversalQueryBus;
import com.neegix.organization.dept.application.service.command.CreateDeptCommand;
import com.neegix.organization.dept.application.service.command.DeleteDeptCommand;
import com.neegix.organization.dept.application.service.command.UpdateDeptCommand;
import com.neegix.organization.dept.application.service.command.mapper.DeptCommandMapper;
import com.neegix.organization.dept.application.service.query.GetDeptDetailQuery;
import com.neegix.organization.dept.application.service.query.GetDeptsQuery;
import com.neegix.organization.dept.application.service.query.mapper.DeptQueryMapper;
import com.neegix.organization.dept.interfaces.form.NewDeptForm;
import com.neegix.organization.dept.interfaces.form.QueryDeptForm;
import com.neegix.organization.dept.interfaces.form.UpdateDeptForm;
import com.neegix.organization.dept.interfaces.vo.DeptVO;
import com.neegix.inferfaces.result.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

 /**
  * This file is auto-generated by nebula-framework-generator.
  * The auto-generation plugin was developed using IntelliJ IDEA Community Edition.
  * Thanks to JetBrains for their outstanding contributions to the developer community.
  * <p>
  * The code generated by this tool is owned by the user of the tool.
  * The tool itself is copyrighted by <a href="https://www.neegix.com">https://www.neegix.com</a>.
  *
  * @author <a href="https://www.neegix.com">https://www.neegix.com</a>
  * @version 1.0.0
  * @since 2024-12-10 14:48:03
  */

@RestController
@RequestMapping("/api/v1/dept")
public class DeptController {

    @Autowired
    private UniversalCommandBus commandBus;

    @Autowired
    private UniversalQueryBus queryBus;

    @PreAuthorize("hasAuthority('organization:dept:add')")
    @PostMapping("/bizUnit/{pkBizUnit}")
    public Result<Void> createDept(@PathVariable("pkBizUnit") Long pkBizUnit, @RequestBody @Valid NewDeptForm deptForm){
        CreateDeptCommand command = DeptCommandMapper.INSTANCE.covertEntity(deptForm);
        command.setPkBizUnit(pkBizUnit);
        return Result.success("创建成功", commandBus.execute(command));
    }

    @PreAuthorize("hasAuthority('organization:dept:modify')")
    @PutMapping("/{id}/bizUnit/{pkBizUnit}")
    public Result<Void> updateDept(@PathVariable Long id, @PathVariable Long pkBizUnit, @RequestBody @Valid UpdateDeptForm deptForm){
        UpdateDeptCommand command = DeptCommandMapper.INSTANCE.covertEntity(deptForm);
        command.setPkBizUnit(pkBizUnit);
        command.setId(id);
        return Result.success("更新成功",commandBus.execute(command));
    }

    @PreAuthorize("hasAuthority('organization:dept:list')")
    @GetMapping("/bizUnit/{pkBizUnit}")
    public Result<List<DeptVO>> getDepts(@PathVariable("pkBizUnit") Long pkBizUnit, @ModelAttribute QueryDeptForm deptForm){
        if(deptForm == null) {
            deptForm = new QueryDeptForm();
        }
        GetDeptsQuery getDeptsQuery = DeptQueryMapper.INSTANCE.covertEntity(deptForm);
        getDeptsQuery.setPkBizUnit(pkBizUnit);
        return Result.success("查询成功",queryBus.execute(getDeptsQuery));
    }

    @PreAuthorize("hasAuthority('organization:dept:get')")
    @GetMapping("/{id}")
    public Result<DeptVO> getDeptById(@PathVariable("id") Long id) {
        return Result.success("获取成功", queryBus.execute(new GetDeptDetailQuery(id)));
    }

    @PreAuthorize("hasAuthority('organization:dept:remove')")
    @DeleteMapping
    public Result<Void> removeDept(@RequestBody List<Long> ids){
        return Result.success("删除成功", commandBus.execute(new DeleteDeptCommand(ids.get(0))));
    }
}