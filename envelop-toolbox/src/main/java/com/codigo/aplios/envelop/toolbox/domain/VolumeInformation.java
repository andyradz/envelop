/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.toolbox.domain;

import com.codigo.aplios.envelop.toolbox.external.winapi.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.IntByReference;

/**
 *
 * @author andrzej.radziszewski
 */
public class VolumeInformation {

    static void getFileSystemName() {
        char[] lpVolumeNameBuffer = new char[256];
        DWORD nVolumeNameSize = new DWORD(256);
        IntByReference lpVolumeSerialNumber = new IntByReference();
        IntByReference lpMaximumComponentLength = new IntByReference();
        IntByReference lpFileSystemFlags = new IntByReference();

        char[] lpFileSystemNameBuffer = new char[256];
        DWORD nFileSystemNameSize = new DWORD(256);

        lpVolumeSerialNumber.setValue(0);
        lpMaximumComponentLength.setValue(256);
        lpFileSystemFlags.setValue(0);

        Kernel32.INSTANCE.GetVolumeInformation(
                "C:\\",
                lpVolumeNameBuffer,
                nVolumeNameSize,
                lpVolumeSerialNumber,
                lpMaximumComponentLength,
                lpFileSystemFlags,
                lpFileSystemNameBuffer,
                nFileSystemNameSize);

        System.out.println("Last error: " + Kernel32.INSTANCE.GetLastError() + "\n\n");

        String fs = new String(lpFileSystemNameBuffer);
        System.out.println(fs.trim());

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        getFileSystemName();
    }

}
