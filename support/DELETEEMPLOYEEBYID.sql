--------------------------------------------------------
--  File created - Friday-June-12-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure DELETEEMPLOYEEBYID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "CJV805_202A10"."DELETEEMPLOYEEBYID" (
        p_empid     IN EMPLOYEES.EMPLOYEE_ID%TYPE) 
IS 
BEGIN
  DELETE FROM EMPLOYEES
  WHERE EMPLOYEE_ID = p_empid;
END DELETEEMPLOYEEBYID;

/
