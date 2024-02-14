import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormArray, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../../../models/user/user.model';
import { GroupsService } from '../../../../services/groups/groups.service';
import { GroupEdit } from '../../../../models/groups/groupEdit.interface';
import { GroupDetails } from '../../../../models/groups/groupDetails.model';
import { swalAlert } from '../../../../utils/sweet-alert';
import { SharedDataService } from '../../../../services/sharedData/shared-data.service';

@Component({
  selector: 'app-group-edit',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './group-edit.component.html',
  styleUrl: './group-edit.component.css'
})
export class GroupEditComponent implements OnChanges, OnInit {
  constructor(
    private groupsService: GroupsService,
    private sharedDataService: SharedDataService
  ) { }

  @Input() user!: User;
  @Input() actualGroup!: GroupDetails;

  @Output() actualGroupChange = new EventEmitter<GroupDetails>();

  groupCategories: any;

  editForm = new FormGroup({
    name: new FormControl(''),
    amount: new FormControl(0),
    categoryId: new FormControl(-1, Validators.min(1)),
    description: new FormControl(''),
  })

  ngOnInit(): void {
    this.groupsService.getGroupCategories().subscribe({
      next: (res: any) => {
        this.groupCategories = res;
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['actualGroup'] && changes['actualGroup'].currentValue) {
      this.editForm.patchValue({
        name: changes['actualGroup'].currentValue.name,
        amount: changes['actualGroup'].currentValue.totalBalance,
        categoryId: changes['actualGroup'].currentValue.groupCategory.id,
        description: changes['actualGroup'].currentValue.description
      });
    }
  }

  handleGroupEdition() {
    if (this.editForm.valid) {
      let group: GroupEdit = {
        name: this.editForm.controls.name.value as string,
        totalBalance: this.editForm.controls.amount.value as number,
        groupCategoryId: this.editForm.controls.categoryId.value as number,
        description: this.editForm.controls.description.value as string
      }

      this.groupsService.updateGroup(group, this.actualGroup.groupId, this.user.id).subscribe({
        next: (res: GroupDetails) => {
          this.actualGroupChange.emit(res);
          this.sharedDataService.updateGroups(this.user);
          swalAlert('success', 'Grupo actualizado', 'El grupo fue actualizado correctamente');
        },
        error: (error: any) => {
          console.log(error)
        }
      });
    }
  }

}
