package com.example.queryBuilder.Payload;


import lombok.Data;

import java.util.List;

@Data
public class RowPayload {

   private List<ColumnPayload>columns;

}
