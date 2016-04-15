/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehcache.spi.loaderwriter;

import java.util.Collections;
import java.util.Map;

/**
 * Thrown by a {@link org.ehcache.Cache Cache} when the {@link CacheLoaderWriter} it uses threw an
 * {@link Exception} while bulk loading values for a given set of keys.
 */
public class BulkCacheLoadingException extends CacheLoadingException {

  private static final long serialVersionUID = -5296309607929568779L;

  private final Map<?, Exception> failures;
  private final Map<?, ?> successes;

  /**
   * Constructs a {@code BulkCacheLoadingException} instance with the given maps.
   * <P>
   *   The given two maps are:
   *   <UL>
   *     <LI>a map from keys to exception thrown while loading,</LI>
   *     <LI>a map from keys to value where loading succeeded</LI>
   *   </UL>
   * </P>
   *
   * @param failures the map of keys to failure encountered while loading
   * @param successes the map of keys successfully loaded and their associated value
   */
  public BulkCacheLoadingException(final Map<?, Exception> failures, final Map<?, ?> successes) {
    this.failures = Collections.unmodifiableMap(failures);
    this.successes = Collections.unmodifiableMap(successes);
  }

  /**
   * Constructs a new exception instance with the given message and maps.
   * <P>
   *   The given two maps are:
   *   <UL>
   *     <LI>a map from keys to exception thrown while loading,</LI>
   *     <LI>a map from keys to value where loading succeeded</LI>
   *   </UL>
   * </P>
   *
   * @param message the exception message
   * @param failures the map of keys to failure encountered while loading
   * @param successes the map of keys successfully loaded and their associated value
   */
  public BulkCacheLoadingException(final String message, final Map<Object, Exception> failures, final Map<Object, Object> successes) {
    super(message);
    this.failures = Collections.unmodifiableMap(failures);
    this.successes = Collections.unmodifiableMap(successes);
  }

  /**
   * Returns the map of keys to exception this exception was constructed with.
   *
   * @return a map of keys to exception encountered while loading
   */
  public Map<?, Exception> getFailures() {
    return failures;
  }

  /**
   * Returns the map of keys to value this exception was constructed with.
   *
   * @return a map of keys to value loaded
   */
  public Map<?, ?> getSuccesses() {
    return successes;
  }
}