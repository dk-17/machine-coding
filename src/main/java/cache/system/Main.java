package cache.system;

import cache.system.service.Cache;
import cache.system.utils.storage.HashMapBasedStorage;
import cache.system.utils.storage.Storage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter cache size: ");
        int cacheSize;
        try {
            cacheSize = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid integer");
            return;
        }

        Storage storage = new HashMapBasedStorage(cacheSize);
        Cache cache = new Cache(cacheSize, storage);

        scanner.nextLine();

        while(true) {
           System.out.println("Enter cache operation (get/ put/ quit) ");
           String operation = scanner.nextLine();

           if(operation.equals("quit")) {
               break;
           }

           switch (operation) {
               case "get":
                   System.out.println("Enter key: ");
                   String getKey = scanner.nextLine();
                   String getValue = cache.get(getKey);
                   System.out.println("Retrieved value: " + getValue);
                   break;

               case "put":
                   System.out.println("Enter key:value pair: ");
                   String getKeyValue = scanner.nextLine();
                   String[] keyValue = getKeyValue.split(":");
                   if (keyValue.length == 2) {
                       cache.put(keyValue[0], keyValue[1]);
                       System.out.println("Key - value pair added to cache.");
                   } else  {
                       System.out.println("Invalid input format for key-value pair.");
                   }
                   break;

               default:
                   System.out.println("Invalid operation. Please enter 'get', 'put' or 'quit' ");
           }
        }
        scanner.close();
    }
}
