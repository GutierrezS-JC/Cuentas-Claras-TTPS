import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authReq = req.clone({
    headers: req.headers.set(
      'Authorization',
      'Bearer ' + (localStorage.getItem('currentUser'))
    ),
  });
  console.log(authReq)
  return next(authReq);
};