/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.envelop.explorer.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.codigo.aplios.envelop.explorer.domain.DriveInfo;
import com.codigo.aplios.envelop.explorer.domain.IDriveInfo;

/**
 *
 * @author andrzej.radziszewski
 */
public class DriveInfoLoader implements IFileCommand {

    public static void main(String[] args) {
        DriveInfoLoader d = new DriveInfoLoader();
        d.execute();
    }

    //TODO: daoać odcztwanie w innym wątku aby przerwać
    private final List<IDriveInfo> driveInfo;

    //dodać filtry
    public DriveInfoLoader() {
        driveInfo = new ArrayList<>();
    }

    @Override
    public void execute() {
        this.process();
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void restart() {
        this.driveInfo.clear();
    }

    @Override
    public void result() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void process() {
        File[] drives = File.listRoots();
        Stream.of(drives).forEach(drive -> {
            //todo tutaj automatyczna wstrzykiwanie implementacji obiektu
            this.driveInfo.add(new DriveInfo(drive));
        });
    }

}
