//package io.github.oleiva.performance; /**
// * Oshi (https://github.com/dblock/oshi)
// *
// * Copyright (c) 2010 - 2016 The Oshi Project Team
// *
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Maintainers:
// * dblock[at]dblock[dot]org
// * widdis[at]gmail[dot]com
// * enrico.bianchi[at]gmail[dot]com
// *
// * Contributors:
// * https://github.com/dblock/oshi/graphs/contributors
// */
////package oshi;
//
//import com.sun.jna.Platform;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//import oshi.SystemInfo;
//import oshi.hardware.CentralProcessor;
//import oshi.hardware.Display;
//import oshi.hardware.GlobalMemory;
//import oshi.hardware.HWDiskStore;
//import oshi.hardware.HardwareAbstractionLayer;
//import oshi.hardware.NetworkIF;
//import oshi.hardware.PowerSource;
//import oshi.hardware.Sensors;
//import oshi.hardware.UsbDevice;
//import oshi.software.os.OperatingSystem;
//import oshi.software.os.OperatingSystemVersion;
//import oshi.util.FormatUtil;
//import oshi.util.Util;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
////import oshi.hardware.CentralProcessor.TickType;
////import oshi.hardware.;
////import oshi.software.os.OperatingSystem.ProcessSort;
//
///**
// * The Class SystemInfoTest.
// *
// * @author dblock[at]dblock[dot]org
// */
//public class SystemInfoTest {
//
//    /**
//     * Test central processor.
//     */
//    @Test
//    public void testCentralProcessor() {
//        SystemInfo si = new SystemInfo();
//        CentralProcessor p = si.getHardware().getProcessor();
//
//        assertNotNull(p.getVendor());
//        assertTrue(p.getVendorFreq() == -1 || p.getVendorFreq() > 0);
//        p.setVendor("v");
//        assertEquals(p.getVendor(), "v");
//
//        assertNotNull(p.getName());
//        p.setName("n");
//        assertEquals(p.getName(), "n");
//
//        assertNotNull(p.getIdentifier());
//        p.setIdentifier("i");
//        assertEquals(p.getIdentifier(), "i");
//
//        p.setCpu64(true);
//        assertTrue(p.isCpu64bit());
//
//        assertNotNull(p.getStepping());
//        p.setStepping("s");
//        assertEquals(p.getStepping(), "s");
//
//        assertNotNull(p.getModel());
//        p.setModel("m");
//        assertEquals(p.getModel(), "m");
//
//        assertNotNull(p.getFamily());
//        p.setFamily("f");
//        assertEquals(p.getFamily(), "f");
//
//        assertTrue(p.getSystemCpuLoadBetweenTicks() >= 0 && p.getSystemCpuLoadBetweenTicks() <= 1);
////        assertEquals(p.getSystemCpuLoadTicks().length, TickType.values().length);
//
//        Util.sleep(500);
//        assertTrue(p.getSystemCpuLoad() >= 0.0 && p.getSystemCpuLoad() <= 1.0);
//        assertEquals(p.getSystemLoadAverage(3).length, 3);
//        if (Platform.isMac() || Platform.isLinux()) {
//            assertTrue(p.getSystemLoadAverage() >= 0.0);
//        }
//
//        assertEquals(p.getProcessorCpuLoadBetweenTicks().length, p.getLogicalProcessorCount());
//        for (int cpu = 0; cpu < p.getLogicalProcessorCount(); cpu++) {
//            assertTrue(p.getProcessorCpuLoadBetweenTicks()[cpu] >= 0 && p.getProcessorCpuLoadBetweenTicks()[cpu] <= 1);
////            assertEquals(p.getProcessorCpuLoadTicks()[cpu].length, TickType.values().length);
//        }
//
//        assertTrue(p.getSystemUptime() > 0);
//        assertNotNull(p.getSystemSerialNumber());
//        assertTrue(p.getLogicalProcessorCount() >= p.getPhysicalProcessorCount());
//        assertTrue(p.getPhysicalProcessorCount() > 0);
//    }
//
//    /**
//     * Test disks extraction.
//     *
//     * @throws IOException
//     *             Signals that an I/O exception has occurred.
//     */
//    @Test
//    public void testDisks() throws IOException {
//        SystemInfo si = new SystemInfo();
//
//        for (HWDiskStore disk : si.getHardware().getDiskStores()) {
//            // NOTE: for now, only tests are for getting disk informations
//            assertNotNull(disk.getName());
//            assertNotNull(disk.getModel());
//            assertNotNull(disk.getSerial());
//            assertNotNull(disk.getSize());
//            assertNotNull(disk.getReads());
//            assertNotNull(disk.getWrites());
//        }
//    }
//
//    /**
//     * Test network interfaces extraction.
//     *
//     * @throws IOException
//     *             Signals that an I/O exception has occurred.
//     */
//    @Test
//    public void testNetworkInterfaces() throws IOException {
//        SystemInfo si = new SystemInfo();
//
//        for (NetworkIF net : si.getHardware().getNetworkIFs()) {
//            assertNotNull(net.getName());
//            assertNotNull(net.getDisplayName());
//            assertNotNull(net.getMacaddr());
//            assertNotNull(net.getIPv4addr());
//            assertNotNull(net.getIPv6addr());
//            assertTrue(net.getBytesRecv() >= 0);
//            assertTrue(net.getBytesSent() >= 0);
//            assertTrue(net.getPacketsRecv() >= 0);
//            assertTrue(net.getPacketsSent() >= 0);
//            assertTrue(net.getSpeed() >= 0);
//            assertTrue(net.getMTU() >= 0);
//        }
//    }
//
//    /**
//     * Test displays
//     */
//    @Test
//    public void testDisplay() {
//        SystemInfo si = new SystemInfo();
//        Display[] displays = si.getHardware().getDisplays();
//        for (Display d : displays) {
//            assertTrue(d.getEdid().length >= 128);
//        }
//    }
//
//    /**
//     * Test GlobalMemory.
//     */
//    @Test
//    public void testGlobalMemory() {
//        SystemInfo si = new SystemInfo();
//        HardwareAbstractionLayer hal = si.getHardware();
//        GlobalMemory memory = hal.getMemory();
//        assertNotNull(memory);
//
//        // RAM tests
//        assertTrue(memory.getTotal() > 0);
//        assertTrue(memory.getAvailable() >= 0);
//        assertTrue(memory.getAvailable() <= memory.getTotal());
//
//        // Swap tests
//        assertTrue(memory.getSwapTotal() >= 0);
//        assertTrue(memory.getSwapUsed() >= 0);
//        assertTrue(memory.getSwapUsed() <= memory.getSwapTotal());
//    }
//
//    /**
//     * Test power source.
//     */
//    @Test
//    public void testPowerSource() {
//        SystemInfo si = new SystemInfo();
//        PowerSource[] psArr = si.getHardware().getPowerSources();
//        for (PowerSource ps : psArr) {
//            assertTrue(ps.getRemainingCapacity() >= 0 && ps.getRemainingCapacity() <= 1);
//            double epsilon = 1E-6;
//            assertTrue(ps.getTimeRemaining() > 0 || Math.abs(ps.getTimeRemaining() - -1) < epsilon
//                    || Math.abs(ps.getTimeRemaining() - -2) < epsilon);
//        }
//    }
//
//    /**
//     * Test sensors
//     */
//    @Test
//    public void testSensors() {
//        SystemInfo si = new SystemInfo();
//        Sensors s = si.getHardware().getSensors();
//        assertTrue(s.getCpuTemperature() >= 0d && s.getCpuTemperature() <= 100d);
//        int[] speeds = s.getFanSpeeds();
//        for (int fan = 0; fan < speeds.length; fan++) {
//            assertTrue(speeds[fan] >= 0);
//        }
//        assertTrue(s.getCpuVoltage() >= 0);
//    }
//
//    /**
//     * Test operating system
//     */
//    @Test
//    public void testOSVersion() {
//        SystemInfo si = new SystemInfo();
//        OperatingSystem os = si.getOperatingSystem();
//        assertNotNull(os.getFamily());
//        assertNotNull(os.getManufacturer());
//        OperatingSystemVersion version = os.getVersion();
//        assertNotNull(version);
//        assertNotNull(version.getVersion());
//        assertNotNull(version.getCodeName());
//        assertNotNull(version.getBuildNumber());
//
////        assertTrue(os.getProcessCount() >= 1);
////        assertTrue(os.getThreadCount() >= 1);
////        assertTrue(os.getProcessId() > 0);
////        assertNotNull(os.getProcesses(0, null));
////        assertEquals(os.getProcess(os.getProcessId()).getProcessID(), os.getProcessId());
//    }
//
//    /**
//     * Test file system.
//     *
//     * @throws IOException
//     *             Signals that an I/O exception has occurred.
//     */
////    @Test
//    public void testFileSystem() throws IOException {
//        SystemInfo si = new SystemInfo();
////        FileSystem filesystem = si.getOperatingSystem().getFileSystem();
////        assertTrue(filesystem.getOpenFileDescriptors() >= 0L);
////        assertTrue(filesystem.getMaxFileDescriptors() >= 0L);
////        OSFileStore[] fs = filesystem.getFileStores();
////        for (int f = 0; f < fs.length; f++) {
////            assertNotNull(fs[f].getName());
////            assertNotNull(fs[f].getVolume());
////            assertNotNull(fs[f].getDescription());
////            assertNotNull(fs[f].getType());
////            assertTrue(fs[f].getTotalSpace() >= 0);
////            assertTrue(fs[f].getUsableSpace() <= fs[f].getTotalSpace());
////        }
////         Hack to extract path from FileStore.toString() is undocumented,
//        // this test will fail if toString format changes
////        if (Platform.isLinux()) {
////            FileStore store = Files.getFileStore((new File("/")).toPath());
////            assertEquals("/", store.toString().replace(" (" + store.name() + ")", ""));
////        }
//    }
//
//    /**
//     * The main method.
//     *
//     * @param args
//     *            the arguments
//     */
//    public static void main(String[] args) {
//        // Options: ERROR > WARN > INFO > DEBUG > TRACE
////        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
//        Logger LOG = LoggerFactory.getLogger(SystemInfoTest.class);
//
//        LOG.info("Initializing System...");
//        SystemInfo si = new SystemInfo();
//
//        // software
//        // software: operating system
//        OperatingSystem os = si.getOperatingSystem();
//        System.out.println(os);
//
//        // hardware
//        HardwareAbstractionLayer hal = si.getHardware();
//
//        // hardware: processors
//        System.out.println(hal.getProcessor());
//        System.out.println(" " + hal.getProcessor().getPhysicalProcessorCount() + " physical CPU(s)");
//        System.out.println(" " + hal.getProcessor().getLogicalProcessorCount() + " logical CPU(s)");
//
//        System.out.println("Identifier: " + hal.getProcessor().getIdentifier());
//        System.out.println("Serial Num: " + hal.getProcessor().getSystemSerialNumber());
//
//        // hardware: memory
//        LOG.info("Checking Memory...");
//        System.out.println("Memory: " + FormatUtil.formatBytes(hal.getMemory().getAvailable()) + "/"
//                + FormatUtil.formatBytes(hal.getMemory().getTotal()));
//        System.out.println("Swap used: " + FormatUtil.formatBytes(hal.getMemory().getSwapUsed()) + "/"
//                + FormatUtil.formatBytes(hal.getMemory().getSwapTotal()));
//        // uptime
//        LOG.info("Checking Uptime...");
//        System.out.println("Uptime: " + FormatUtil.formatElapsedSecs(hal.getProcessor().getSystemUptime()));
//
//        // CPU
//        LOG.info("Checking CPUa...");
//        long[] prevTicks = hal.getProcessor().getSystemCpuLoadTicks();
//        System.out.println("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
//        // Wait a second...
//        Util.sleep(1000);
//        long[] ticks = hal.getProcessor().getSystemCpuLoadTicks();
//        System.out.println("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
//
////        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
////        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
////        long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
////        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
////        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
////        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
////        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
////        long totalCpu = user + nice + sys + idle + iowait + irq + softirq;
//
////        System.out.format(
////                "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%%%n",
////                100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
////                100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu);
//        System.out.format("CPU load: %.1f%% (counting ticks)%n",
//                hal.getProcessor().getSystemCpuLoadBetweenTicks() * 100);
//        System.out.format("CPU load: %.1f%% (OS MXBean)%n", hal.getProcessor().getSystemCpuLoad() * 100);
//        double[] loadAverage = hal.getProcessor().getSystemLoadAverage(3);
//        System.out.println("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
//                + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
//                + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
//        // per core CPU
//        StringBuilder procCpu = new StringBuilder("CPU load per processor:");
//        double[] load = hal.getProcessor().getProcessorCpuLoadBetweenTicks();
//        for (int cpu = 0; cpu < load.length; cpu++) {
//            procCpu.append(String.format(" %.1f%%", load[cpu] * 100));
//        }
//        System.out.println(procCpu.toString());
//
//        // Processes
////        System.out.println("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
//        // Sort by highest CPU
////        List<OSProcess> procs = Arrays.asList(os.getProcesses(5, ProcessSort.CPU));
//
//        System.out.println("   PID  %CPU %MEM       VSZ       RSS Name");
////        for (int i = 0; i < procs.size() && i < 5; i++) {
////            OSProcess p = procs.get(i);
////            System.out.format(" %5d %5.1f %4.1f %9s %9s %s%n", p.getProcessID(),
////                    100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
////                    100d * p.getResidentSetSize() / hal.getMemory().getTotal(),
////                    FormatUtil.formatBytes(p.getVirtualSize()), FormatUtil.formatBytes(p.getResidentSetSize()),
////                    p.getName());
////        }
//
//        // hardware: sensors
//        LOG.info("Checking Sensors...");
//        System.out.println("Sensors:");
//        System.out.format(" CPU Temperature: %.1f°C%n", hal.getSensors().getCpuTemperature());
//        System.out.println(" Fan Speeds:" + Arrays.toString(hal.getSensors().getFanSpeeds()));
//        System.out.format(" CPU Voltage: %.1fV%n", hal.getSensors().getCpuVoltage());
//
//        // hardware: power
//        LOG.info("Checking Power sources...");
//        StringBuilder sb = new StringBuilder("Power: ");
//        if (hal.getPowerSources().length == 0) {
//            sb.append("Unknown");
//        } else {
//            double timeRemaining = hal.getPowerSources()[0].getTimeRemaining();
//            if (timeRemaining < -1d) {
//                sb.append("Charging");
//            } else if (timeRemaining < 0d) {
//                sb.append("Calculating time remaining");
//            } else {
//                sb.append(String.format("%d:%02d remaining", (int) (timeRemaining / 3600),
//                        (int) (timeRemaining / 60) % 60));
//            }
//        }
//        for (PowerSource pSource : hal.getPowerSources()) {
//            sb.append(String.format("%n %s @ %.1f%%", pSource.getName(), pSource.getRemainingCapacity() * 100d));
//        }
//        System.out.println(sb.toString());
//
//        // hardware: disks
//        LOG.info("Checking Disks...");
//        System.out.println("Disks:");
//
//        HWDiskStore[] dskArray = hal.getDiskStores();
//        for (HWDiskStore dsk : dskArray) {
//            boolean readwrite = dsk.getReads() > 0 || dsk.getWrites() > 0;
//            System.out.format(" %s: (model: %s - S/N: %s) size: %s, reads: %s, writes: %s %n", dsk.getName(),
//                    dsk.getModel(), dsk.getSerial(),
//                    dsk.getSize() > 0 ? FormatUtil.formatBytesDecimal(dsk.getSize()) : "?",
//                    readwrite ? FormatUtil.formatBytes(dsk.getReads()) : "?",
//                    readwrite ? FormatUtil.formatBytes(dsk.getWrites()) : "?");
//        }
//
//        // software: file system
//        LOG.info("Checking File System...");
//        System.out.println("File System:");
//
////        FileSystem filesystem = os.getFileSystem();
//
////        System.out.format(" File Descriptors: %d/%d%n", filesystem.getOpenFileDescriptors(),
////                filesystem.getMaxFileDescriptors());
//
////        OSFileStore[] fsArray = filesystem.getFileStores();
////        for (OSFileStore fs : fsArray) {
////            long usable = fs.getUsableSpace();
////            long total = fs.getTotalSpace();
////            System.out.format(" %s (%s) [%s] %s of %s free (%.1f%%) is %s and is mounted at %s%n", fs.getName(),
////                    fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
////                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
////                    fs.getVolume(), fs.getMount());
////        }
//
//        // hardware: network interfaces
//        LOG.info("Checking Network interfaces...");
//        System.out.println("Network interfaces:");
//
//        NetworkIF[] netArray = hal.getNetworkIFs();
//        for (NetworkIF net : netArray) {
//            System.out.format(" Name: %s (%s)%n", net.getName(), net.getDisplayName());
//            System.out.format("   MAC Address: %s %n", net.getMacaddr());
//            System.out.format("   MTU: %s, Speed: %s %n", net.getMTU(), FormatUtil.formatValue(net.getSpeed(), "bps"));
//            System.out.format("   IPv4: %s %n", Arrays.toString(net.getIPv4addr()));
//            System.out.format("   IPv6: %s %n", Arrays.toString(net.getIPv6addr()));
//            boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
//                    || net.getPacketsSent() > 0;
//            System.out.format("   Traffic: received %s/%s; transmitted %s/%s %n",
//                    hasData ? net.getPacketsRecv() + " packets" : "?",
//                    hasData ? FormatUtil.formatBytes(net.getBytesRecv()) : "?",
//                    hasData ? net.getPacketsSent() + " packets" : "?",
//                    hasData ? FormatUtil.formatBytes(net.getBytesSent()) : "?");
//        }
//
//        // hardware: displays
//        LOG.info("Checking Displays...");
//        System.out.println("Displays:");
//        int i = 0;
//        for (Display display : hal.getDisplays()) {
//            System.out.println(" Display " + i + ":");
//            System.out.println(display.toString());
//            i++;
//        }
//
//        // hardware: USB devices
//        LOG.info("Checking USB Devices...");
//        System.out.println("USB Devices:");
//        for (UsbDevice usbDevice : hal.getUsbDevices()) {
//            System.out.println(usbDevice.toString());
//        }
//    }
//
//
//}
