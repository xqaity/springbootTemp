package component.upload.service.ipml;/**
 * @author Created by lenovo
 * @date 2022/6/6 14:54
 */

import org.springframework.core.io.Resource;
import component.upload.service.FileStorageService;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * <h3>xq-mode</h3>
 * <p>文件上传接口实现类</p>
 *
 * @author : xq
 * @date : 2022-06-06 14:54
 **/
@Service("fileStorageService")
public class FileStorageServiceImpl implements FileStorageService {
	private final Path path = Paths.get("fileStorage");
	@Override
	public void init() {
		try {
			Files.createDirectory(path);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void save(MultipartFile multipartFile) {
		try {
			Files.copy(multipartFile.getInputStream(),this.path.resolve(multipartFile.getOriginalFilename()));
		} catch (IOException e) {
			throw new RuntimeException("Could not store the file. Error:"+e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		Path file = path.resolve(filename);
		try {
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()){
				return resource;
			}else{
				throw new RuntimeException("Could not read the file.");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error:"+e.getMessage());
		}
	}

	@Override
	public Stream<Path> load() {
		try {
			return Files.walk(this.path,1)
					.filter(path -> !path.equals(this.path))
					.map(this.path::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files.");
		}
	}

	@Override
	public void clear() {
		FileSystemUtils.deleteRecursively(path.toFile());
	}
}
