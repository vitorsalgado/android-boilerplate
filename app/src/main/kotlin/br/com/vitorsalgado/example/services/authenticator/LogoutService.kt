package br.com.vitorsalgado.example.services.authenticator

import android.support.annotation.WorkerThread

import io.reactivex.Completable

@FunctionalInterface
interface LogoutService {
  @WorkerThread
  fun logout(): Completable
}
