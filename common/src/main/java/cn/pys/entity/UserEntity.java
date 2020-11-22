package cn.pys.entity;

import cn.pys.group.AddGroup;
import cn.pys.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description
 * @Date 2020/11/12 16:08
 * @Created by pengys
 */
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -3577259992996352084L;

    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Integer id;

    @NotBlank(message = "name不能为空", groups = {AddGroup.class})
    private String name;

    @NotNull(message = "年龄不能为空", groups = {AddGroup.class})
    @Min(1)
    @Max(100)
    private Integer age;
}
