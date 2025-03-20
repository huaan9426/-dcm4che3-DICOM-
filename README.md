# -dcm4che3-DICOM-
元数据读取、DICOM转PNG、标签编辑、图像显示
pom.xml
    <properties>
          .......
          *******
        <dcm4che.version>5.26.0</dcm4che.version>
    </properties>

    <repositories>
        <!-- 添加dcm4che官方仓库 -->
        <repository>
            <id>dcm4che-releases</id>
            <url>https://www.dcm4che.org/maven2</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
    <dependencies>
        <!-- DCM4CHE 核心库 -->
        <dependency>
            <groupId>org.dcm4che</groupId>
            <artifactId>dcm4che-core</artifactId>
            <version>${dcm4che.version}</version>
        </dependency>

        <!-- DICOM图像处理 -->
        <dependency>
            <groupId>org.dcm4che</groupId>
            <artifactId>dcm4che-imageio</artifactId>
            <version>${dcm4che.version}</version>
        </dependency>

        <!-- 图像显示支持 -->
        <dependency>
            <groupId>org.dcm4che</groupId>
            <artifactId>dcm4che-image</artifactId>
            <version>${dcm4che.version}</version>
        </dependency>

        <!-- 日志 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.30</version>
        </dependency>
    </dependencies>
