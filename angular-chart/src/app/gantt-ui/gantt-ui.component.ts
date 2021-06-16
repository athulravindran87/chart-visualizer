import { Component, OnInit, ViewChild} from '@angular/core';
import { Validators, FormBuilder } from "@angular/forms";
import { GanttEditorComponent, GanttEditorOptions } from 'ng-gantt';
import { NgxCaptureService } from 'ngx-capture';

@Component({
  selector: 'app-gantt-ui',
  templateUrl: './gantt-ui.component.html',
  styleUrls: ['./gantt-ui.component.css']
})
export class GanttUiComponent implements OnInit {

  @ViewChild('editor')
  editor: GanttEditorComponent = new GanttEditorComponent;
  public editorOptions: GanttEditorOptions = new GanttEditorOptions;
  public data: any;
  imgBase64: any=''
  
  constructor(private captureService:NgxCaptureService) { }

  ngOnInit(): void {

    this.data = this.initialData();
    this.editorOptions = {
      vFormat: "day",
      vEditable: true,
      vShowDur: 0,
      vShowStartDate: 0,
      vShowEndDate: 0,
      vShowRes: 0,
      vFormatArr: ['Week', 'Month', 'Quarter'],
      vQuarterColWidth: 60,
      vRowHeight: 30,
      vShowComp: 0,
        
      vEventsChange: {
        taskname: () => {
          console.log("taskname");
        }
      }
    };



  }
  @ViewChild('screen', { static: true }) screen: any;

  capture()
  {
    this.captureService.getImage(this.screen.nativeElement, true).toPromise().then(img=>{
      console.log(img);
      this.imgBase64=img;
      this.save('GanttChart');
     
    })
  }

  DataURIToBlob(dataURI: string) {
    const splitDataURI = dataURI.split(',');
    const byteString = splitDataURI[0].indexOf('base64') >= 0 ? atob(splitDataURI[1]) : decodeURI(splitDataURI[1]);
    const mimeString = splitDataURI[0].split(':')[1].split(';')[0];
    const ia = new Uint8Array(byteString.length)
    for (let i = 0; i < byteString.length; i++)
        ia[i] = byteString.charCodeAt(i);
      
        return new Blob([ia], { type: mimeString });
}


  save(fileName: string){
    var a = document.createElement('a');
    var file = this.DataURIToBlob(this.imgBase64);
    a.href = URL.createObjectURL(file);
    a.download = fileName;
    a.click();

  }

  initialData(){
    return [

      {
        pID: 1,
        pName: "Arden Station",
        pStart: "",
        pEnd: "",
        pClass: "ggroupblack",
        pLink: "",
        pMile: 0,
        pRes: "Prasanna",
        pComp: 0,
        pGroup: 1,
        pParent: 0,
        pOpen: 1,
        pDepend: "",
        pCaption: "",
        pNotes: "Notes about station"
      },

      {
        pID: 11,
        pName: "Piling Works",
        pStart: "2018-04-18",
        pEnd: "2018-12-20",
        pClass: "gmilestone",
        pLink: "",
        pMile: 1,
        pRes: "Ana",
        pComp: 100,
        pGroup: 0,
        pParent: 1,
        pOpen: 1,
        pDepend: "",
        pCaption: "",
        pNotes: "Gem is to show that this task has been completed and achieved a milestone"
      },

      {
        pID: 12,
        pName: "Excavation Works",
        pStart: "2018-08-10",
        pEnd: "2019-06-14",
        pClass: "gtaskblue",
        pLink: "",
        pMile: 0,
        pRes: "Bob",
        pComp: 20,
        pGroup: 0,
        pParent: 1,
        pOpen: 1,
        pDepend: "",
        pCaption: "",
        pNotes: ""

      },
      {
        pID: 13,
        pName: "Primary Structure",
        pStart: "2019-11-08",
        pEnd: "2020-05-08",
        pClass: "gtaskred",
        pLink: "",
        pMile: 0,
        pRes: "Cathy",
        pComp: 60,
        pGroup: 0,
        pParent: 1,
        pOpen: 1,
        pDepend: "",
        pCaption: "",
        pNotes: ""

      },

      {

        pID: 14,
        pName: "Secondary Structure",
        pStart: "2020-07-11",
        pEnd: "2021-05-01",
        pClass: "gtaskyellow",
        pLink: "",
        pMile: 0,
        pRes: "Dan",
        pComp: 10,
        pGroup: 0,
        pParent: 1,
        pOpen: 1,
        pDepend: "13",
        pCaption: "",
        pNotes: ""


      },

      {

        pID: 2,
        pName: "Perungalathur Station",
        pStart: "",
        pEnd: "",
        pClass: "ggroupblack",
        pLink: "",
        pMile: 0,
        pRes: "Aks",
        pComp: 0,
        pGroup: 1,
        pParent: 0,
        pOpen: 1,
        pDepend: "",
        pCaption: "",
        pNotes: "Notes about station"

      },

      {

        pID: 21,
        pName: "Piling Works",
        pStart: "2019-04-18",
        pEnd: "2019-12-20",
        pClass: "gtaskyellow",
        pLink: "",
        pMile: 0,
        pRes: "Ana",
        pComp: 8,
        pGroup: 0,
        pParent: 2,
        pOpen: 1,
        pDepend: "",
        pCaption: "",
        pNotes: "Project Description"

      },

    ];
  }



}
