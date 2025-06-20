package com.neegix.organization.psn.interfaces.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
  * @since 2024-12-11 15:13:32
  */

@Data
public class NewPsnForm {
    // 出生日期
    private String birth;
    // 人员编码
    @NotBlank(message="人员编码不能为空")
    private String code;
    // 联系电话
    private String contactTel;
    // 是否删除（0 否 1 是）
    private Boolean deleted;
    // 邮箱
    private String email;
    // 是否启用（0 否 1 是）
    private Boolean enabled;
    // 性别
    private Long gender;
    // 参加工作日期
    private String hireDate;
    // 家庭住址
    private String homeAddress;
    // 家庭电话
    private String homeTel;
    // 证件号码
    private String idNo;
    // 证件类型
    private Long idType;
    // 手机号
    private String mobilePhone;
    // 人员名称
    @NotBlank(message="人员名称不能为空")
    private String name;
    // 人员昵称
    private String nickname;
    // 业务单元PK
    private Long pkBizUnit;
    @Valid
    private List<PsnWorkInfoForm> psnWorkInfos;
}


