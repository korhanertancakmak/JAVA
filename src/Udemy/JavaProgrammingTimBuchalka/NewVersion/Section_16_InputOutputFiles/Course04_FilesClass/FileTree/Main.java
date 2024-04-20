package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course04_FilesClass.FileTree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
/**                                 Walking the File Tree
 *
 *      This method walks the file tree, depth first, as does the walk method of Files. Depth first means that the code
 *  will recursively visit all the child elements before visiting any of a folder's siblings.
 *
 *              <DIR> out                   <DIR> src
 *                      <OIR> dev                   <DIR> dev
 *                           Walker.class                   Walker.java
 *
 *  If our cwd has 2 sub folders, out and source, the walk methods won't first examine all the first level paths, then
 *  examine the second level. Instead, it examines the first path, and if that's a folder, than it will walk into that
 *  folder, and so on. This probably feels rather natural, to programmers, and seems a bit like a stack trace.
 *
 *      The alternative is breadth first which means any dependent nodes are walked after the sibling nodes. But remember,
 *  walk and walkFileTree are depth first. This is important because it is depth first, the Files.walkFileTree method,
 *  provides a mechanism to accumulate information, about all the children, up to the parent. Java provides entry points
 *  in the walk to execute operations, through a FileVisitor interface. This subs out methods you can implement, at certain
 *  events in your walk. These events are:
 *
 *  * Before visiting a directory.
 *  * After visiting a directory.
 *  * When visiting a file.
 *  * A failure to visit a file.
 *
 * **/
    public static void main(String[] args) {

        Path startingPath = Path.of("..");
        FileVisitor<Path> statsVisitor = new StatsVisitor(1);
        try {
            Files.walkFileTree(startingPath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class StatsVisitor extends SimpleFileVisitor<Path> {

        //private int level;
        private Path initialPath = null;
        private final Map<Path, Long> folderSizes = new LinkedHashMap<>();      // we used LinkedHashMap, because we want to maintain the insertion order
        private int initialCount;

        private int printLevel;

        public StatsVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {

            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);
            //System.out.println("\t".repeat(level + 1) + file.getFileName());
            folderSizes.merge(file.getParent(), 0L, (o, n) -> o += attrs.size());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException {

            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);
            //level++;
            //System.out.println("\t".repeat(level) + dir.getFileName());
            if (initialPath == null) {
                initialPath = dir;
                initialCount = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - initialCount;
                if (relativeLevel == 1) {
                    folderSizes.clear();
                }
                folderSizes.put(dir, 0L);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                throws IOException {

            Objects.requireNonNull(dir);
//            if (exc != null)
//                throw exc;
            //level--;
            if (dir.equals(initialPath)) {
                return FileVisitResult.TERMINATE;
            }

            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                folderSizes.forEach((key, value) -> {

                    int level = key.getNameCount() - initialCount - 1;
                    if (level < printLevel) {
                        System.out.printf("%s[%s] - %,d bytes %n",
                                "\t".repeat(level), key.getFileName(), value);
                    }
                });

            } else {
                long folderSize = folderSizes.get(dir);
                folderSizes.merge(dir.getParent(), 0L, (o, n) -> o += folderSize);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
