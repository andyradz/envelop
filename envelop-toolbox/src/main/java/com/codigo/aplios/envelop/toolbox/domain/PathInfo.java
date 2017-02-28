/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.toolbox.domain;

import com.codigo.aplios.envelop.toolbox.control.IPathVisitor;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrzej.radziszewski
 */
public abstract class PathInfo implements IPathInfo {

    private final Path pathName;

    //------------------------------------------------------------------------------------------------------------------
    public PathInfo(String pathName) throws IllegalArgumentException {
        this(Paths.get(pathName));
    }

    //------------------------------------------------------------------------------------------------------------------
    public PathInfo(IDirectoryInfo pathName) throws IllegalArgumentException {
        this(Paths.get(pathName.fullName()));
    }

    //------------------------------------------------------------------------------------------------------------------
    public PathInfo(File pathName) throws IllegalArgumentException {
        this(pathName.toPath());
    }

    //------------------------------------------------------------------------------------------------------------------
    public PathInfo(URI pathName) throws IllegalArgumentException {
        this(Paths.get(pathName));
    }

    //------------------------------------------------------------------------------------------------------------------
    public Path path() {
        return this.pathName;
    }

    //------------------------------------------------------------------------------------------------------------------
    protected PathInfo(Path pathName) throws IllegalArgumentException {
        this.pathName = pathName;
    }

    //------------------------------------------------------------------------------------------------------------------
    public IFileInfo.FileAttributes attributes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDateTime creationTime() {
        BasicFileAttributes pathAttr;
        try {
            pathAttr = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class).readAttributes();
            return LocalDateTime.from(pathAttr.creationTime().toInstant());
        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public LocalDateTime creationTimeUtc() {
        BasicFileAttributes pathAttr;
        try {
            pathAttr = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class).readAttributes();
            final ZonedDateTime utcDt = LocalDateTime.from(pathAttr.creationTime().toInstant()).atZone(ZoneId.systemDefault());
            final ZonedDateTime utcZoned = utcDt.withZoneSameInstant(ZoneId.of("UTC"));
            return utcDt.toLocalDateTime();
        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String name() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String fullName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String localName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRoot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDirectory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long deeph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long capacity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long length() {
        return this.pathName.getFileName().toString().length();
    }

    @Override
    public String type() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String description() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> atributes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Image icon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public abstract void accept(IPathVisitor visitor);

    @Override
    public LocalDateTime lastAccessTime() {
        BasicFileAttributes pathAttr;
        try {
            pathAttr = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class).readAttributes();
            return LocalDateTime.from(pathAttr.lastAccessTime().toInstant());
        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    @Override
    public LocalDateTime lastAccessTimeUtc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalDateTime lastWriteTime() {
        BasicFileAttributes pathAttr;
        try {
            pathAttr = Files.getFileAttributeView(this.pathName, BasicFileAttributeView.class).readAttributes();
            return LocalDateTime.from(pathAttr.lastModifiedTime().toInstant());
        } catch (IOException ex) {
            Logger.getLogger(PathInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LocalDateTime.MIN;
    }

    @Override
    public LocalDateTime lastWriteTimeUtc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
