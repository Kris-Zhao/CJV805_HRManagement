--------------------------------------------------------
--  File created - Thursday-June-11-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package P_SECURITY
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE PACKAGE "CJV805_202A10"."P_SECURITY" AS
        TYPE cur_EmpInfo IS REF CURSOR;

    FUNCTION F_SECURITY(
        P_SECID IN SECURITY.SEC_ID%TYPE,
        P_SECPASSWORD IN SECURITY.SEC_PASSWORD%TYPE)
    RETURN NUMBER;

    PROCEDURE P_EMP_INFO (
        P_EMPLOYEEID IN EMPLOYEES.EMPLOYEE_ID%TYPE, 
        p_info  OUT cur_EmpInfo);
    END P_SECURITY; 

/
