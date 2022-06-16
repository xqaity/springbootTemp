package component.upload;/**
 * @author Created by lenovo
 * @date 2022/6/6 15:14
 */

import component.upload.service.FileStorageService;
import component.upload.valueobject.Message;
import component.upload.valueobject.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h3>xq-mode</h3>
 * <p>文件上传控制器</p>
 *
 * @author : xq
 * @date : 2022-06-06 15:14
 **/
@RestController
public class FileUploadController {

	@Autowired
	FileStorageService fileStorageService;

	@PostMapping("/upload")
	public ResponseEntity<Message> upload(@RequestParam("file") MultipartFile file){
		try {
			fileStorageService.save(file);
			return ResponseEntity.ok(new Message("Upload file successfully: "+file.getOriginalFilename()));
		}catch (Exception e){
			return ResponseEntity.badRequest()
					.body(new Message("Could not upload the file:"+file.getOriginalFilename()));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<UploadFile>> files(){
		List<UploadFile> files = fileStorageService.load()
				.map(path -> {
					String fileName = path.getFileName().toString();
					String url = MvcUriComponentsBuilder
							.fromMethodName(FileUploadController.class,
									"getFile",
									path.getFileName().toString()
							).build().toString();
					return new UploadFile(fileName,url);
				}).collect(Collectors.toList());
		return ResponseEntity.ok(files);
	}

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable("filename")String filename){
		Resource file = fileStorageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment;filename=\""+file.getFilename()+"\"")
				.body(file);
	}
}
