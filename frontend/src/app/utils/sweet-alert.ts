import Swal, { SweetAlertIcon, SweetAlertResult } from "sweetalert2";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 2500,
  timerProgressBar: true,
});

export const swalToast = (icon: SweetAlertIcon, title: string): Promise<SweetAlertResult<any>> => {
  return Toast.fire({
    icon: icon,
    title: title
  });
}

export const swalAlert = (icon: SweetAlertIcon, title: string, text: string): Promise<SweetAlertResult<any>> => {
  return Swal.fire({
    icon: icon,
    title: title,
    text: text,
    confirmButtonColor: "#0a0a0a",
  });
}

