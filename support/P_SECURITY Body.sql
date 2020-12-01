--------------------------------------------------------
--  File created - Thursday-June-11-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package Body P_SECURITY
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE PACKAGE BODY "CJV805_202A10"."P_SECURITY" AS

  FUNCTION F_SECURITY(
        P_SECID IN SECURITY.SEC_ID%TYPE,
        P_SECPASSWORD IN SECURITY.SEC_PASSWORD%TYPE)
    RETURN NUMBER IS
    employeeID SECURITY.employee_id%TYPE;
  BEGIN
  
    -- TODO: Implementation required for FUNCTION P_SECURITY.F_SECURITY
    SELECT employee_id INTO employeeID
    from security 
    where sec_id=P_SECID AND sec_password= P_SECPASSWORD AND sec_status='A';
     
    RETURN employeeID;

  EXCEPTION 
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
    
  END F_SECURITY;

  PROCEDURE P_EMP_INFO (
        P_EMPLOYEEID IN EMPLOYEES.EMPLOYEE_ID%TYPE, 
        p_info  OUT cur_EmpInfo) IS
  BEGIN
    -- TODO: Implementation required for PROCEDURE P_SECURITY.P_EMP_INFO
    OPEN p_info FOR SELECT * FROM EMPLOYEES
    WHERE EMPLOYEE_ID = P_EMPLOYEEID;
    
  END P_EMP_INFO;

END P_SECURITY;

/
