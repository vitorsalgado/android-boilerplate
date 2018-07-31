package br.com.vitorsalgado.example.services.authenticator

import androidx.annotation.WorkerThread

import io.reactivex.Completable

@FunctionalInterface
interface LogoutService {
  @WorkerThread
  fun logout(): Completable
}
