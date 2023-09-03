package com.dermont.residentialInfo;

import com.dermont.storedItems.Items;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResidentialFileWriter {


    public void writeResidentialToFile(Residential residential, String fileName) throws IOException {


        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("Stan osob zamieszkujacych osiedle " + residential.getResidentialName());
        writer.newLine();


        List<Space> sortedSpaces = residential.getBlocks().stream()
                .flatMap(block -> block.getSpaces().stream())
                .sorted(Comparator.comparing(space -> space.getDimensions().getCapacity()))
                .collect(Collectors.toList());


        for (Space sortedSpace : sortedSpaces) {

            writer.write("Pomieszczenie " + sortedSpace.getId() + ", rozmiar: " + sortedSpace.getDimensions().getCapacity() + "m^3");
            writer.newLine();

            if (sortedSpace instanceof Flat) {
                Flat flat = (Flat) sortedSpace;
                flat.getTenants().forEach(person -> {
                    try {
                        writer.write("Lokator: ");
                        writer.newLine();
                        writer.write(person.displayInfoAsString());
                        writer.write(person.displayInfoAsString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }

            if (sortedSpace instanceof ParkingSpace) {

                ParkingSpace parkingSpace = (ParkingSpace) sortedSpace;
                List<Items> sortedItems = parkingSpace.getStoredItems().stream()
                        .sorted(
                                Comparator.comparing(items -> items.getDimensions().getCapacity()))
                        .collect(Collectors.toList());


                for (Items sortedItem : sortedItems) {
                    writer.write("Przedmiot: " + sortedItem.getName() + ", rozmiar: " + sortedItem.getDimensions().getCapacity() + "m^3");
                    writer.newLine();
                }
                writer.newLine();
            }
        }

        writer.flush();
        System.out.println("Dane zostaly zapisane do pliku: " + fileName);

    }
}

