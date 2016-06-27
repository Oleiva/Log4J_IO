package io.github.oleiva.test;


import com.sun.jna.Platform;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.Display;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.PowerSource;
import oshi.hardware.Sensors;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystemVersion;
import oshi.util.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class SystemInfoTest2 {




    /**
     * Test central processor.
     */

    public void testCentralProcessor() {



        SystemInfo si = new SystemInfo();
        CentralProcessor p = si.getHardware().getProcessor();

        assertNotNull(p.getVendor());
        assertTrue(p.getVendorFreq() == -1 || p.getVendorFreq() > 0);
        p.setVendor("v");
        assertEquals(p.getVendor(), "v");

        assertNotNull(p.getName());
        p.setName("n");
        assertEquals(p.getName(), "n");

        assertNotNull(p.getIdentifier());
        p.setIdentifier("i");
        assertEquals(p.getIdentifier(), "i");

        p.setCpu64(true);
        assertTrue(p.isCpu64bit());

        assertNotNull(p.getStepping());
        p.setStepping("s");
        assertEquals(p.getStepping(), "s");

        assertNotNull(p.getModel());
        p.setModel("m");
        assertEquals(p.getModel(), "m");

        assertNotNull(p.getFamily());
        p.setFamily("f");
        assertEquals(p.getFamily(), "f");

        assertTrue(p.getSystemCpuLoadBetweenTicks() >= 0 && p.getSystemCpuLoadBetweenTicks() <= 1);
//        assertEquals(p.getSystemCpuLoadTicks().length, TickType.values().length);

        Util.sleep(500);
        assertTrue(p.getSystemCpuLoad() >= 0.0 && p.getSystemCpuLoad() <= 1.0);
        assertEquals(p.getSystemLoadAverage(3).length, 3);
        if (Platform.isMac() || Platform.isLinux()) {
            assertTrue(p.getSystemLoadAverage() >= 0.0);
        }

        assertEquals(p.getProcessorCpuLoadBetweenTicks().length, p.getLogicalProcessorCount());
        for (int cpu = 0; cpu < p.getLogicalProcessorCount(); cpu++) {
            assertTrue(p.getProcessorCpuLoadBetweenTicks()[cpu] >= 0 && p.getProcessorCpuLoadBetweenTicks()[cpu] <= 1);
//            assertEquals(p.getProcessorCpuLoadTicks()[cpu].length, TickType.values().length);
        }

        assertTrue(p.getSystemUptime() > 0);
        assertNotNull(p.getSystemSerialNumber());
        assertTrue(p.getLogicalProcessorCount() >= p.getPhysicalProcessorCount());
        assertTrue(p.getPhysicalProcessorCount() > 0);
    }

    /**
     * Test disks extraction.
     */
    @Test
    public void testDisks() throws IOException {
        SystemInfo si = new SystemInfo();

        for (HWDiskStore disk : si.getHardware().getDiskStores()) {
            // NOTE: for now, only tests are for getting disk informations
            assertNotNull(disk.getName());
            assertNotNull(disk.getModel());
            assertNotNull(disk.getSerial());
            assertNotNull(disk.getSize());
            assertNotNull(disk.getReads());
            assertNotNull(disk.getWrites());
        }
    }

    /**
     * Test network interfaces extraction.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testNetworkInterfaces() throws IOException {
        SystemInfo si = new SystemInfo();

        for (NetworkIF net : si.getHardware().getNetworkIFs()) {
            assertNotNull(net.getName());
            assertNotNull(net.getDisplayName());
            assertNotNull(net.getMacaddr());
            assertNotNull(net.getIPv4addr());
            assertNotNull(net.getIPv6addr());
            assertTrue(net.getBytesRecv() >= 0);
            assertTrue(net.getBytesSent() >= 0);
            assertTrue(net.getPacketsRecv() >= 0);
            assertTrue(net.getPacketsSent() >= 0);
            assertTrue(net.getSpeed() >= 0);
            assertTrue(net.getMTU() >= 0);
        }
    }

    /**
     * Test displays
     */
    @Test
    public void testDisplay() {
        SystemInfo si = new SystemInfo();
        Display[] displays = si.getHardware().getDisplays();
        for (Display d : displays) {
            assertTrue(d.getEdid().length >= 128);
        }
    }

    /**
     * Test GlobalMemory.
     */
    @Test
    public void testGlobalMemory() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory memory = hal.getMemory();
        assertNotNull(memory);

        // RAM tests
        assertTrue(memory.getTotal() > 0);
        assertTrue(memory.getAvailable() >= 0);
        assertTrue(memory.getAvailable() <= memory.getTotal());

        // Swap tests
        assertTrue(memory.getSwapTotal() >= 0);
        assertTrue(memory.getSwapUsed() >= 0);
        assertTrue(memory.getSwapUsed() <= memory.getSwapTotal());
    }

    /**
     * Test power source.
     */
    @Test
    public void testPowerSource() {
        SystemInfo si = new SystemInfo();
        PowerSource[] psArr = si.getHardware().getPowerSources();
        for (PowerSource ps : psArr) {
            assertTrue(ps.getRemainingCapacity() >= 0 && ps.getRemainingCapacity() <= 1);
            double epsilon = 1E-6;
            assertTrue(ps.getTimeRemaining() > 0 || Math.abs(ps.getTimeRemaining() - -1) < epsilon
                    || Math.abs(ps.getTimeRemaining() - -2) < epsilon);
        }
    }

    /**
     * Test sensors
     */
    @Test
    public void testSensors() {
        SystemInfo si = new SystemInfo();
        Sensors s = si.getHardware().getSensors();
        assertTrue(s.getCpuTemperature() >= 0d && s.getCpuTemperature() <= 100d);
        int[] speeds = s.getFanSpeeds();
        for (int fan = 0; fan < speeds.length; fan++) {
            assertTrue(speeds[fan] >= 0);
        }
        assertTrue(s.getCpuVoltage() >= 0);
    }

    /**
     * Test operating system
     */
    @Test
    public void testOSVersion() {
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        assertNotNull(os.getFamily());
        assertNotNull(os.getManufacturer());
        OperatingSystemVersion version = os.getVersion();
        assertNotNull(version);
        assertNotNull(version.getVersion());
        assertNotNull(version.getCodeName());
        assertNotNull(version.getBuildNumber());

//        assertTrue(os.getProcessCount() >= 1);
//        assertTrue(os.getThreadCount() >= 1);
//        assertTrue(os.getProcessId() > 0);
//        assertNotNull(os.getProcesses(0, null));
//        assertEquals(os.getProcess(os.getProcessId()).getProcessID(), os.getProcessId());
    }

    /**
     * Test file system.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
//    @Test
    public void testFileSystem() throws IOException {
        SystemInfo si = new SystemInfo();
//        FileSystem filesystem = si.getOperatingSystem().getFileSystem();
//        assertTrue(filesystem.getOpenFileDescriptors() >= 0L);
//        assertTrue(filesystem.getMaxFileDescriptors() >= 0L);
//        OSFileStore[] fs = filesystem.getFileStores();
//        for (int f = 0; f < fs.length; f++) {
//            assertNotNull(fs[f].getName());
//            assertNotNull(fs[f].getVolume());
//            assertNotNull(fs[f].getDescription());
//            assertNotNull(fs[f].getType());
//            assertTrue(fs[f].getTotalSpace() >= 0);
//            assertTrue(fs[f].getUsableSpace() <= fs[f].getTotalSpace());
//        }
//         Hack to extract path from FileStore.toString() is undocumented,
        // this test will fail if toString format changes
//        if (Platform.isLinux()) {
//            FileStore store = Files.getFileStore((new File("/")).toPath());
//            assertEquals("/", store.toString().replace(" (" + store.name() + ")", ""));
//        }
    }




}
