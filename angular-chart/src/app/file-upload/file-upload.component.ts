import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
  selectedFile: any = null;
  constructor(private http: HttpClient) { }

  onFileSelected(event: any){

    this.selectedFile = <File>event.target.files[0];
  }

  onUpload(){

    const fd = new FormData();
    fd.append('xlsx', this.selectedFile, this.selectedFile.name);

    this.http.post('localhost:8080/upload', fd).subscribe(res => {

      console.log(res);
    });

  }

  ngOnInit(): void {
  }

}
