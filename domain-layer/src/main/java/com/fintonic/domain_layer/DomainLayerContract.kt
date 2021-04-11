package com.fintonic.domain_layer

import arrow.core.Either
import com.fintonic.domain_layer.domain.FailureBo
import kotlinx.coroutines.*

interface DomainLayerContract {


    /**
     * Defines the requirements for an entity to become the 'presentation-layer' of the app
     */
    interface PresentationLayer {

        /**
         * Defines baseline use-case using Kotlin Coroutines
         */
        interface UseCase<in T, out S> {
            /**
             * Initiates the use-case using certain arguments related to Kotlin Coroutines
             *
             * @param [scope] the [CoroutineScope] used, which will cancel and terminate this entity when required
             * @param [params] arguments used in the query. It is null by default.
             * @param [onResult] a callback to return a value at some point
             * @param [dispatcherWorker] the dispatcher used to perform the use-case (Dispatchers.IO by default)
             */
            fun invoke(
                scope: CoroutineScope,
                params: T? = null,
                onResult: (Either<FailureBo, S>) -> Unit,
                dispatcherWorker: CoroutineDispatcher = Dispatchers.IO
            ) {
                // Task undertaken in a dispatcher worker and once completed, result sent in the scope thread
                scope.launch { withContext(dispatcherWorker) { onResult(run(params)) } }
            }

            /**
             * Executes the previously defined use-cause
             *
             * @param [param] arguments used in the query
             * @return An [S] value if successful or a [FailureBo] otherwise
             */
            suspend fun run(params: T? = null): Either<FailureBo, S>
        }
    }

    /**
     * Defines the requirements for an entity to become the 'data-layer' of the app
     */
    interface DataLayer {

        companion object {
            const val BEER_LIST_REPOSITORY_TAG = "beerListRepsotory"
        }


        interface BeersListRepository<out T> {
            suspend fun fetchBeersList(): Either<FailureBo, T>
        }
    }
}

