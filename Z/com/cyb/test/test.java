package com.cyb.test;

import com.cyb.MonitorInfoBean;
import com.cyb.dao.IMonitorService;
import com.cyb.dao.MonitorServiceImpl;

public class test {
    public static void main(String[] args) throws Exception {  
        IMonitorService service = new MonitorServiceImpl();  
        MonitorInfoBean monitorInfo = service.getMonitorInfoBean();  
        System.out.println("cpuռ����=" + monitorInfo.getCpuRatio());  
          
        System.out.println("��ʹ���ڴ�=" + monitorInfo.getTotalMemory());  
        System.out.println("ʣ���ڴ�=" + monitorInfo.getFreeMemory());  
        System.out.println("����ʹ���ڴ�=" + monitorInfo.getMaxMemory());  
          
        System.out.println("����ϵͳ=" + monitorInfo.getOsName());  
        System.out.println("�ܵ������ڴ�=" + monitorInfo.getTotalMemorySize() + "kb");  
        System.out.println("ʣ��������ڴ�=" + monitorInfo.getFreeMemory() + "kb");  
        System.out.println("��ʹ�õ������ڴ�=" + monitorInfo.getUsedMemory() + "kb");  
        System.out.println("�߳�����=" + monitorInfo.getTotalThread() + "kb");  
    }  
}
