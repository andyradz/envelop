/**
 * 
 */
package com.codigo.aplios.envelop.explorer.domain;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.VerRsrc.VS_FIXEDFILEINFO;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author andrzej.radziszewski
 *
 * Code file  : EXEFileInfo.java
 * Create date: 16.03.2017
 */
public class EXEFileInfo {

	public static void main(String[] args) {
		EXEFileInfo.getBuildOfProgram("c:\\Windows\\bfsvc.exe");
	}

	public static int MAJOR = 0;
	public static int MINOR = 1;
	public static int BUILD = 2;
	public static int REVISION = 3;

	public static int getMajorVersionOfProgram(String path) {
		return getVersionInfo(path)[MAJOR];
	}

	public static int getMinorVersionOfProgram(String path) {
		return getVersionInfo(path)[MINOR];
	}

	public static int getBuildOfProgram(String path) {
		return getVersionInfo(path)[BUILD];
	}

	public static int getRevisionOfProgram(String path) {
		return getVersionInfo(path)[REVISION];
	}

	public static int[] getVersionInfo(String path) {
		IntByReference dwDummy = new IntByReference();
		dwDummy.setValue(0);

		int versionlength = com.sun.jna.platform.win32.Version.INSTANCE.GetFileVersionInfoSize(path, dwDummy);

		byte[] bufferarray = new byte[versionlength];
		Pointer lpData = new Memory(bufferarray.length);
		PointerByReference lplpBuffer = new PointerByReference();
		IntByReference puLen = new IntByReference();
		boolean fileInfoResult =
				com.sun.jna.platform.win32.Version.INSTANCE.GetFileVersionInfo(path, 0, versionlength, lpData);
		boolean verQueryVal =
				com.sun.jna.platform.win32.Version.INSTANCE.VerQueryValue(lpData, "\\", lplpBuffer, puLen);

		VS_FIXEDFILEINFO lplpBufStructure = new VS_FIXEDFILEINFO(lplpBuffer.getValue());
		lplpBufStructure.read();

		int v1 = (lplpBufStructure.dwFileVersionMS).intValue() >> 16;
		int v2 = (lplpBufStructure.dwFileVersionMS).intValue() & 0xffff;
		int v3 = (lplpBufStructure.dwFileVersionLS).intValue() >> 16;
		int v4 = (lplpBufStructure.dwFileVersionLS).intValue() & 0xffff;
		System.out.println("Version: " + v1 + "." + v2 + "." + v3 + "." + v4);
		return new int[] { v1, v2, v3, v4 };
	}
}
/*
 * Const VS_FFI_SIGNATURE = &HFEEF04BD
 * Const VS_FFI_STRUCVERSION = &H10000
 * Const VS_FFI_FILEFLAGSMASK = &H3F&
 * Const VS_FF_DEBUG = &H1
 * Const VS_FF_PRERELEASE = &H2
 * Const VS_FF_PATCHED = &H4
 * Const VS_FF_PRIVATEBUILD = &H8
 * Const VS_FF_INFOINFERRED = &H10
 * Const VS_FF_SPECIALBUILD = &H20
 * Const VOS_UNKNOWN = &H0
 * Const VOS_DOS = &H10000
 * Const VOS_OS216 = &H20000
 * Const VOS_OS232 = &H30000
 * Const VOS_NT = &H40000
 * Const VOS__BASE = &H0
 * Const VOS__WINDOWS16 = &H1
 * Const VOS__PM16 = &H2
 * Const VOS__PM32 = &H3
 * Const VOS__WINDOWS32 = &H4
 * Const VOS_DOS_WINDOWS16 = &H10001
 * Const VOS_DOS_WINDOWS32 = &H10004
 * Const VOS_OS216_PM16 = &H20002
 * Const VOS_OS232_PM32 = &H30003
 * Const VOS_NT_WINDOWS32 = &H40004
 * Const VFT_UNKNOWN = &H0
 * Const VFT_APP = &H1
 * Const VFT_DLL = &H2
 * Const VFT_DRV = &H3
 * Const VFT_FONT = &H4
 * Const VFT_VXD = &H5
 * Const VFT_STATIC_LIB = &H7
 * Const VFT2_UNKNOWN = &H0
 * Const VFT2_DRV_PRINTER = &H1
 * Const VFT2_DRV_KEYBOARD = &H2
 * Const VFT2_DRV_LANGUAGE = &H3
 * Const VFT2_DRV_DISPLAY = &H4
 * Const VFT2_DRV_MOUSE = &H5
 * Const VFT2_DRV_NETWORK = &H6
 * Const VFT2_DRV_SYSTEM = &H7
 * Const VFT2_DRV_INSTALLABLE = &H8
 * Const VFT2_DRV_SOUND = &H9
 * Const VFT2_DRV_COMM = &HA
 * Private Type VS_FIXEDFILEINFO
 * dwSignature As Long
 * dwStrucVersionl As Integer ' e.g. = &h0000 = 0
 * dwStrucVersionh As Integer ' e.g. = &h0042 = .42
 * dwFileVersionMSl As Integer ' e.g. = &h0003 = 3
 * dwFileVersionMSh As Integer ' e.g. = &h0075 = .75
 * dwFileVersionLSl As Integer ' e.g. = &h0000 = 0
 * dwFileVersionLSh As Integer ' e.g. = &h0031 = .31
 * dwProductVersionMSl As Integer ' e.g. = &h0003 = 3
 * dwProductVersionMSh As Integer ' e.g. = &h0010 = .1
 * dwProductVersionLSl As Integer ' e.g. = &h0000 = 0
 * dwProductVersionLSh As Integer ' e.g. = &h0031 = .31
 * dwFileFlagsMask As Long ' = &h3F for version "0.42"
 * dwFileFlags As Long ' e.g. VFF_DEBUG Or VFF_PRERELEASE
 * dwFileOS As Long ' e.g. VOS_DOS_WINDOWS16
 * dwFileType As Long ' e.g. VFT_DRIVER
 * dwFileSubtype As Long ' e.g. VFT2_DRV_KEYBOARD
 * dwFileDateMS As Long ' e.g. 0
 * dwFileDateLS As Long ' e.g. 0
 * End Type
 * Private Declare Function GetFileVersionInfo Lib "Version.dll" Alias "GetFileVersionInfoA" (ByVal lptstrFilename As
 * String, ByVal dwhandle As Long, ByVal dwlen As Long, lpData As Any) As Long
 * Private Declare Function GetFileVersionInfoSize Lib "Version.dll" Alias "GetFileVersionInfoSizeA" (ByVal
 * lptstrFilename As String, lpdwHandle As Long) As Long
 * Private Declare Function VerQueryValue Lib "Version.dll" Alias "VerQueryValueA" (pBlock As Any, ByVal lpSubBlock As
 * String, lplpBuffer As Any, puLen As Long) As Long
 * Private Declare Sub MoveMemory Lib "kernel32" Alias "RtlMoveMemory" (dest As Any, ByVal Source As Long, ByVal length
 * As Long)
 * Dim Filename As String, Directory As String, FullFileName As String
 * Dim StrucVer As String, FileVer As String, ProdVer As String
 * Dim FileFlags As String, FileOS As String, FileType As String, FileSubType As String
 * Private Sub DisplayVerInfo()
 * Dim rc As Long, lDummy As Long, sBuffer() As Byte
 * Dim lBufferLen As Long, lVerPointer As Long, udtVerBuffer As VS_FIXEDFILEINFO
 * Dim lVerbufferLen As Long
 * 
 * '*** Get size ****
 * lBufferLen = GetFileVersionInfoSize(FullFileName, lDummy)
 * If lBufferLen < 1 Then
 * MsgBox "No Version Info available!"
 * Exit Sub
 * End If
 * 
 * '**** Store info to udtVerBuffer struct ****
 * ReDim sBuffer(lBufferLen)
 * rc = GetFileVersionInfo(FullFileName, 0&, lBufferLen, sBuffer(0))
 * rc = VerQueryValue(sBuffer(0), "\", lVerPointer, lVerbufferLen)
 * MoveMemory udtVerBuffer, lVerPointer, Len(udtVerBuffer)
 * 
 * '**** Determine Structure Version number - NOT USED ****
 * StrucVer = Format$(udtVerBuffer.dwStrucVersionh) & "." & Format$(udtVerBuffer.dwStrucVersionl)
 * 
 * '**** Determine File Version number ****
 * FileVer = Format$(udtVerBuffer.dwFileVersionMSh) & "." & Format$(udtVerBuffer.dwFileVersionMSl) & "." &
 * Format$(udtVerBuffer.dwFileVersionLSh) & "." & Format$(udtVerBuffer.dwFileVersionLSl)
 * 
 * '**** Determine Product Version number ****
 * ProdVer = Format$(udtVerBuffer.dwProductVersionMSh) & "." & Format$(udtVerBuffer.dwProductVersionMSl) & "." &
 * Format$(udtVerBuffer.dwProductVersionLSh) & "." & Format$(udtVerBuffer.dwProductVersionLSl)
 * 
 * '**** Determine Boolean attributes of File ****
 * FileFlags = ""
 * If udtVerBuffer.dwFileFlags And VS_FF_DEBUG Then FileFlags = "Debug "
 * If udtVerBuffer.dwFileFlags And VS_FF_PRERELEASE Then FileFlags = FileFlags & "PreRel "
 * If udtVerBuffer.dwFileFlags And VS_FF_PATCHED Then FileFlags = FileFlags & "Patched "
 * If udtVerBuffer.dwFileFlags And VS_FF_PRIVATEBUILD Then FileFlags = FileFlags & "Private "
 * If udtVerBuffer.dwFileFlags And VS_FF_INFOINFERRE Then FileFlags = FileFlags & "Info "
 * If udtVerBuffer.dwFileFlags And VS_FF_SPECIALBUILD Then FileFlags = FileFlags & "Special "
 * If udtVerBuffer.dwFileFlags And VFT2_UNKNOWN Then FileFlags = FileFlags + "Unknown "
 * 
 * '**** Determine OS for which file was designed ****
 * Select Case udtVerBuffer.dwFileOS
 * Case VOS_DOS_WINDOWS16
 * FileOS = "DOS-Win16"
 * Case VOS_DOS_WINDOWS32
 * FileOS = "DOS-Win32"
 * Case VOS_OS216_PM16
 * FileOS = "OS/2-16 PM-16"
 * Case VOS_OS232_PM32
 * FileOS = "OS/2-16 PM-32"
 * Case VOS_NT_WINDOWS32
 * FileOS = "NT-Win32"
 * Case other
 * FileOS = "Unknown"
 * End Select
 * Select Case udtVerBuffer.dwFileType
 * Case VFT_APP
 * FileType = "App"
 * Case VFT_DLL
 * FileType = "DLL"
 * Case VFT_DRV
 * FileType = "Driver"
 * Select Case udtVerBuffer.dwFileSubtype
 * Case VFT2_DRV_PRINTER
 * FileSubType = "Printer drv"
 * Case VFT2_DRV_KEYBOARD
 * FileSubType = "Keyboard drv"
 * Case VFT2_DRV_LANGUAGE
 * FileSubType = "Language drv"
 * Case VFT2_DRV_DISPLAY
 * FileSubType = "Display drv"
 * Case VFT2_DRV_MOUSE
 * FileSubType = "Mouse drv"
 * Case VFT2_DRV_NETWORK
 * FileSubType = "Network drv"
 * Case VFT2_DRV_SYSTEM
 * FileSubType = "System drv"
 * Case VFT2_DRV_INSTALLABLE
 * FileSubType = "Installable"
 * Case VFT2_DRV_SOUND
 * FileSubType = "Sound drv"
 * Case VFT2_DRV_COMM
 * FileSubType = "Comm drv"
 * Case VFT2_UNKNOWN
 * FileSubType = "Unknown"
 * End Select
 * Case VFT_FONT
 * FileType = "Font"
 * Select Case udtVerBuffer.dwFileSubtype
 * Case VFT_FONT_RASTER
 * FileSubType = "Raster Font"
 * Case VFT_FONT_VECTOR
 * FileSubType = "Vector Font"
 * Case VFT_FONT_TRUETYPE
 * FileSubType = "TrueType Font"
 * End Select
 * Case VFT_VXD
 * FileType = "VxD"
 * Case VFT_STATIC_LIB
 * FileType = "Lib"
 * Case Else
 * FileType = "Unknown"
 * End Select
 * End Sub
 * Private Sub Form_Load()
 * 'KPD-Team 2000
 * 'URL: [url]http://www.allapi.net/[/url]
 * 'E-Mail: [email]KPDTeam@Allapi.net[/email]
 * 'Source -> MS Knowledge Base
 * 'set the file
 * Filename = "kernel32.dll"
 * Directory = "c:\windows\system\"
 * FullFileName = Directory + Filename
 * 'set graphics mode to persistent
 * Me.AutoRedraw = True
 * 'retrieve the information
 * DisplayVerInfo
 * 'show the results
 * Me.Print "Full filename: " + FullFileName
 * Me.Print "File version: " + FileVer
 * Me.Print "Product version: " + ProdVer
 * Me.Print "File flags: " + FileFlags
 * Me.Print "File OS: " + FileOS
 * Me.Prin
 */