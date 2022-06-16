package component.upload.service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author Created by lenovo
 * @date 2022/6/6 14:53
 */
public interface FileStorageService {
	void init();

	void save(MultipartFile multipartFile);

	Resource load(String filename);

	Stream<Path> load();

	void clear();
}
