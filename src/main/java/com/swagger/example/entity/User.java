package com.swagger.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User objesi", description = "Model obj açıklaması ")
public class User {

  @ApiModelProperty(value = "id değeri")
  private int id;
  @ApiModelProperty(value = "isim değeri")
  private String name;
  @ApiModelProperty(value = "tarih değeri")
  private Date date;

}
